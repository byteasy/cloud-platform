package com.byteasy.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.byteasy.common.event.EventPublishUtil;
import com.byteasy.common.util.CastUtil;
import com.byteasy.dto.Column;
import com.byteasy.event.EntityDefineChangeEvent;
import com.byteasy.event.EntityDefineChangeSource;
import com.byteasy.platform.entity.SchemaDefine;
import com.byteasy.platform.mapper.SchemaDefineMapper;
import com.byteasy.platform.service.ISchemaDefineService;
import com.byteasy.platform.vo.SchemaClassTreeVO;
import org.apache.commons.compress.utils.Lists;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 列配置表（存储业务实体字段的展示/校验/表单等配置） 服务实现类
 * </p>
 *
 * @author devops
 * @since 2026-03-13
 */
@Service
public class SchemaDefineServiceImpl extends ServiceImpl<SchemaDefineMapper, SchemaDefine> implements ISchemaDefineService {

    @Override
    public List<Column> queryByClassName(String className) {
        LambdaUpdateWrapper<SchemaDefine> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(SchemaDefine::getClassName, className);
        lambdaUpdateWrapper.orderByAsc(SchemaDefine::getShowOrder);
        List<SchemaDefine> schemaDefines = list(lambdaUpdateWrapper);
        return CastUtil.copyList(schemaDefines, Column.class);
    }

    @Override
    public boolean saveOrUpdate(SchemaDefine entity) {
        if (entity.getId() != null) {
            EventPublishUtil.publishEvent(new EntityDefineChangeEvent(new EntityDefineChangeSource(entity.getClassName())));
        }
        if (entity.getShowOrder() == null) {
            entity.setShowOrder(getMaxShowOrder(entity.getClassName()));
        }
        return super.saveOrUpdate(entity);
    }

    protected Integer getMaxShowOrder(String className) {
        LambdaUpdateWrapper<SchemaDefine> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(SchemaDefine::getClassName, className);
        wrapper.orderByDesc(SchemaDefine::getShowOrder);
        SchemaDefine schemaDefine = getOne(wrapper, false);
        if (schemaDefine == null) {
            return 1;
        }
        return schemaDefine.getShowOrder() + 1;
    }

    @Override
    public void syncSchemaMetaInfo(String serviceName, String className, List<SchemaDefine> schemaDefines) {
//        Class<?> cls = Class.forName(className);
//        List<Column> columns = ColumnUtil.getColumns(cls).values().stream().sorted(Comparator.comparingInt(Column::getShowOrder)).toList();
        for (SchemaDefine schemaDefine : schemaDefines) {
            schemaDefine.setServiceName(serviceName);
            SchemaDefine db = getBy(schemaDefine.getClassName(), schemaDefine.getName());

            if (db == null) {
                save(schemaDefine);
            }
        }
    }

    @Override
    public List<SchemaClassTreeVO> listServicesClassTree() {
        LambdaQueryWrapper<SchemaDefine> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.groupBy(SchemaDefine::getServiceName, SchemaDefine::getClassName);
        lambdaQueryWrapper.select(SchemaDefine::getServiceName, SchemaDefine::getClassName);
        lambdaQueryWrapper.orderByAsc(SchemaDefine::getServiceName, SchemaDefine::getClassName);
        List<SchemaDefine> list = list(lambdaQueryWrapper);

        Map<String, List<SchemaDefine>> map = list.stream().collect(Collectors.groupingBy(SchemaDefine::getServiceName));
        List<SchemaClassTreeVO> schemaClassTreeVOList = Lists.newArrayList();
        for (Map.Entry<String, List<SchemaDefine>> entry : map.entrySet()) {
            SchemaClassTreeVO schemaClassTreeVO = new SchemaClassTreeVO();
            schemaClassTreeVO.setName(entry.getKey());

            List<SchemaClassTreeVO> childrenList = Lists.newArrayList();
            for (SchemaDefine schemaDefine : entry.getValue()) {
                SchemaClassTreeVO child = new SchemaClassTreeVO();
                child.setName(schemaDefine.getClassName());
                childrenList.add(child);
            }
            schemaClassTreeVO.setChildren(childrenList);
            schemaClassTreeVOList.add(schemaClassTreeVO);
        }
        return schemaClassTreeVOList;
    }

    protected SchemaDefine getBy(String className, String fieldName) {
        LambdaUpdateWrapper<SchemaDefine> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(SchemaDefine::getClassName, className);
        lambdaUpdateWrapper.eq(SchemaDefine::getName, fieldName);
        lambdaUpdateWrapper.last("limit 1");
        return getOne(lambdaUpdateWrapper, false);
    }
}
