package com.byteasy.platform.util;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.PolyglotException;
import org.graalvm.polyglot.Value;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * JS脚本执行器：前端配置脚本后台中转执行
 * 1. 传入前端参数Map，注入JS全局 params
 * 2. 执行自定义JS脚本
 * 3. 将JS返回对象转为Java原生Map/List，可直接序列化返回前端
 */
public class JsScriptExecutor {

    /**
     * 执行JS脚本
     *
     * @param params 前端传递的业务对象（接口入参json转Map）
     * @param jsScript    用户自定义JS脚本字符串
     * @return 脚本执行结果，纯Java基础对象（Map/List/String/Number/Boolean/null），可直接序列化返回前端
     */
    public static Object runScript(String bindParamName, Map<String, Object> params, String jsScript) {
        // 1. 参数校验
        if (jsScript == null || jsScript.isBlank()) {
            throw new IllegalArgumentException("JS脚本不能为空");
        }
        Map<String, Object> bindParams = params == null ? new HashMap<>() : params;

        // 2. 构建安全上下文，禁止IO、禁止访问Java类，隔离风险
        try (Context context = Context.newBuilder("js")
                .allowAllAccess(false)
                .allowIO(false)
                .allowHostAccess(false)
                .allowCreateThread(false)
                .build()) {

            // 3. 将前端传入的参数注入JS全局变量 params，脚本内直接使用 params.xxx
            Value jsBindings = context.getBindings("js");
            jsBindings.putMember(bindParamName, javaObjectToGraalValue(jsBindings, bindParams));

            // 4. 执行用户自定义脚本
            Value resultValue;
            try {
                resultValue = context.eval("js", jsScript);
            } catch (PolyglotException e) {
                throw new RuntimeException("JS脚本执行失败：" + formatJsError(e), e);
            }

            // 5. 将Graal Value递归转为Java原生对象，方便序列化返回前端
            return convertGraalValueToJavaObject(resultValue);
        }
    }

    private static Value javaObjectToGraalValue(Value globalBindings, Object obj) {
        if (obj == null) {
            return globalBindings.getContext().asValue(null);
        }
        if (obj instanceof Map<?,?>) {
            Map<String, Object> map = (Map<String, Object>) obj;
            Value jsObj = globalBindings.getContext().eval("js", "({})");
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                Value childVal = javaObjectToGraalValue(globalBindings, entry.getValue());
                jsObj.putMember(entry.getKey(), childVal);
            }
            return jsObj;
        }
        if (obj instanceof Iterable<?>) {
            Iterable<?> list = (Iterable<?>) obj;
            Value jsArr = globalBindings.getContext().eval("js", "[]");
            long idx = 0;
            for (Object item : list) {
                Value childVal = javaObjectToGraalValue(globalBindings, item);
                jsArr.setArrayElement(idx++, childVal);
            }
            return jsArr;
        }
        // 基础类型直接包装
        return globalBindings.getContext().asValue(obj);
    }

    private static Object convertGraalValueToJavaObject(Value val) {
        if (val.isNull()) {
            return null;
        }

        // JS Date 对象转ISO字符串（已修复executeMember）
        if (val.hasMember("getTime") && val.getMember("getTime").canExecute()) {
            Value toIsoFn = val.getMember("toISOString");
            return toIsoFn.execute().asString();
        }

        // JS数组 → List
        if (val.hasArrayElements()) {
            List<Object> list = new ArrayList<>();
            long arraySize = val.getArraySize();
            for (long i = 0; i < arraySize; i++) {
                list.add(convertGraalValueToJavaObject(val.getArrayElement(i)));
            }
            return list;
        }

        // 修复：不再使用 isObject()，改用 hasMembers() 判断JS普通对象
        if (val.hasMembers() && !val.isHostObject()) {
            Map<String, Object> map = new HashMap<>();
            val.getMemberKeys().forEach(key -> {
                Value childVal = val.getMember(key);
                map.put(key, convertGraalValueToJavaObject(childVal));
            });
            return map;
        }

        // 基础数据类型
        if (val.isString()) {
            return val.asString();
        }
        if (val.isBoolean()) {
            return val.asBoolean();
        }
        if (val.isNumber()) {
            if (val.fitsInInt()) {
                return val.asInt();
            } else if (val.fitsInLong()) {
                return val.asLong();
            } else {
                return val.asDouble();
            }
        }

        // 兜底转字符串
        return val.toString();
    }

    /**
     * 格式化JS异常信息，输出错误行号、错误内容，方便前端排查
     */
    private static String formatJsError(PolyglotException e) {
        StringBuilder sb = new StringBuilder();
        sb.append(e.getMessage());
        if (e.getSourceLocation() != null) {
            sb.append("，行号：").append(e.getSourceLocation().getStartLine());
        }
        if (e.isSyntaxError()) {
            sb.append("（语法错误）");
        }
        return sb.toString();
    }
}