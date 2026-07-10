package com.byteasy.platform.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.byteasy.annotations.FieldMeta;
import com.byteasy.entity.AuditEntity;
import com.byteasy.enums.*;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 字段定义表
 * </p>
 *
 * @author devops
 * @since 2026-03-13
 */
@Getter
@Setter
@TableName("platform_schema_define")
@FieldMeta(name = "SchemaDefine", description = "列配置表（存储业务实体字段的展示/校验/表单等配置）")
public class SchemaDefine extends AuditEntity<Long> {

    @TableField("service_name")
    @FieldMeta(name = "服务", isSearch = true, showOrder = 101, width = 240, externalClass = ServiceInstance.class, externalClassPrimaryField = "code", isRequire = true)
    private String serviceName;

    @TableField("class_name")
    @FieldMeta(name = "类", isSearch = true, showOrder = 102, width = 240, isRequire = true)
    private String className;

    @TableField("name")
    @FieldMeta(name = "属性名", isSearch = true, showOrder = 103, isRequire = true)
    private String name;

    @TableField("field_name")
    @FieldMeta(name = "字段名", isSearch = true, showOrder = 104, isRequire = true)
    private String fieldName;

    @TableField("display_name")
    @FieldMeta(name = "显示名", showOrder = 105, maxLength = 80, isI18n = true)
    private String displayName;

    @TableField("description")
    @FieldMeta(name = "说明", showOrder = 106, isI18n = true)
    private String description;

    @TableField("type")
    @FieldMeta(name = "字段类型", showOrder = 107)
    private ColumnType type;

    @TableField("type_class_name")
    @FieldMeta(name = "字段类名", showOrder = 108)
    private String typeClassName;

    @TableField("is_show")
    @FieldMeta(name = "是否显示", showOrder = 109)
    private YesNo isShow;

    @TableField("is_overflow")
    @FieldMeta(name = "是否省略", showOrder = 110)
    private YesNo isOverflow;

    @TableField("is_i18n")
    @FieldMeta(name = "是否多语言", showOrder = 111)
    private YesNo isI18n;

    @TableField("show_order")
    @FieldMeta(name = "显示顺序", showOrder = 112)
    private Integer showOrder;

    @TableField("width")
    @FieldMeta(name = "显示宽度", showOrder = 113)
    private Integer width;

    @TableField("is_require")
    @FieldMeta(name = "是否必填", showOrder = 114)
    private YesNo isRequire;

    @TableField("is_form")
    @FieldMeta(name = "是否表单", showOrder = 115)
    private YesNo isForm;

    @TableField("is_sort")
    @FieldMeta(name = "是否排序", showOrder = 116)
    private YesNo isSort;

    @TableField("is_search")
    @FieldMeta(name = "是否搜索", showOrder = 117)
    private YesNo isSearch;

    @TableField("is_fixed")
    @FieldMeta(name = "是否固定", showOrder = 118, description = "在Table内是否固定")
    private YesNo isFixed;

    @TableField("encrypt")
    @FieldMeta(name = "是否加密", showOrder = 119)
    private YesNo encrypt;

    @TableField("desensitize")
    @FieldMeta(name = "是否脱敏", showOrder = 120)
    private YesNo desensitize;

    @TableField("align")
    @FieldMeta(name = "对齐方式", showOrder = 121, description = "在Table内的对齐方式")
    private Align align;

    @TableField("max_length")
    @FieldMeta(name = "最大长度", showOrder = 122)
    private Integer maxLength;

    @TableField("min_length")
    @FieldMeta(name = "最小长度", showOrder = 123)
    private Integer minLength;

    @TableField("max")
    @FieldMeta(name = "最大值", showOrder = 124)
    private Integer max;

    @TableField("min")
    @FieldMeta(name = "最小值", showOrder = 125)
    private Integer min;

    @TableField("precision")
    @FieldMeta(name = "精度", showOrder = 126)
    private Integer precision;

    @TableField("multiple")
    @FieldMeta(name = "是否多选", showOrder = 127, description = "当表格组件被用于对象筛选时起作用，控制是单选还是多选")
    private YesNo multiple;

    @TableField("pattern")
    @FieldMeta(name = "校验规则", showOrder = 128, description = "自定义校验规则")
    private ValidatorPattern pattern;

    @TableField("relation_type")
    @FieldMeta(name = "关联类型", showOrder = 129, description = "目前支持外键关联和明细表关联，1对1或者1对多")
    private RelationType relationType;

    @TableField("external_class")
    @FieldMeta(name = "关联对象类名", showOrder = 130)
    private String externalClass;

    @TableField("external_class_primary_field")
    @FieldMeta(name = "关联对象字段", showOrder = 131)
    private String externalClassPrimaryField;

    @TableField("inner_primary_field")
    @FieldMeta(name = "明细表关联字段", showOrder = 132, description = "关联类型为明细表时数据库无真实字段，需要指明本对象的关联字段名")
    private String innerPrimaryField;

    @TableField("external_class_display_field")
    @FieldMeta(name = "关联对象展示字段", showOrder = 133, description = "用于展示关联对象的字段名称")
    private String externalClassDisplayField;

    @TableField("ui_component")
    @FieldMeta(name = "关联对象前端组件", description = "当字段是外键类型用于查询条件关联选择或者表单录入的时候关联选择", showOrder = 134)
    private UiComponent uiComponent;

    @TableField("field_component")
    @FieldMeta(name = "表单自定义组件", description = "绑定到字段上的自定义前端组件,查询表单和编辑表单共用", showOrder = 135, maxLength = 512)
    private String fieldComponent;

    @TableField("column_component")
    @FieldMeta(name = "表格列自定义组件", description = "Table Column自定义组件", showOrder = 136, maxLength = 512)
    private String columnComponent;
}
