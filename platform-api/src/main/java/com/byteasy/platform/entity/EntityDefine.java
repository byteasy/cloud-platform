package com.byteasy.platform.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.byteasy.annotations.FieldMeta;
import com.byteasy.entity.AuditEntity;
import com.byteasy.enums.UiComponent;
import com.byteasy.enums.YesNo;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 平台模型定义
 * </p>
 *
 * @author devops
 * @since 2026-05-27
 */
@Getter
@Setter
@TableName("platform_entity_define")
@FieldMeta(name = "EntityDefine", description = "平台模型定义")
public class EntityDefine extends AuditEntity<Long> {

    @FieldMeta(name = "编码", showOrder = 100, maxLength = 512, isSearch = true)
    @TableField("code")
    private String code;

    @FieldMeta(name = "服务", showOrder = 101, isSearch = true, externalClass = ServiceInstance.class, externalClassPrimaryField = "code", uiComponent = UiComponent.TABLE)
    @TableField("service_code")
    private String serviceCode;

    @FieldMeta(name = "名称", showOrder = 102, maxLength = 512, isI18n = true, isSearch = true)
    @TableField("name")
    private String name;

    @FieldMeta(name = "接口地址", showOrder = 103, maxLength = 512, isSearch = true)
    @TableField("url")
    private String url;

    @FieldMeta(name = "数据库表名", showOrder = 104, maxLength = 1024)
    @TableField("table_name")
    private String tableName;

    @FieldMeta(name = "说明", showOrder = 105, maxLength = 1024)
    @TableField("remark")
    private String remark;

    @FieldMeta(name = "标题属性列", showOrder = 106)
    @TableField("title_column_name")
    private String titleColumnName;

    @FieldMeta(name = "操作列宽度", showOrder = 107)
    @TableField("operation_column_width")
    private Integer operationColumnWidth;

    @FieldMeta(name = "默认查询条件", showOrder = 108)
    @TableField("query_params")
    private String queryParams;

    @FieldMeta(name = "表单组件", showOrder = 109)
    @TableField("form_component")
    private String formComponent;

    @FieldMeta(name = "表单组件宽度", showOrder = 110)
    @TableField("form_width")
    private String formWidth;

    @FieldMeta(name = "启用对话框", showOrder = 111)
    @TableField("dialog_model")
    private YesNo dialogModel;

    @FieldMeta(name = "启用分页插件", showOrder = 112)
    @TableField("pagination_visible")
    private YesNo paginationVisible;

    @FieldMeta(name = "启用Excel导入导出", showOrder = 113)
    @TableField("excel_enable")
    private YesNo excelEnable;

    @FieldMeta(name = "启用查询条件", showOrder = 114)
    @TableField("query_form_visible")
    private YesNo queryFormVisible;

    @FieldMeta(name = "Excel导入组件", showOrder = 115)
    @TableField("excel_import_component")
    private String excelImportComponent;

    @FieldMeta(name = "RowKey", showOrder = 116)
    @TableField("row_key")
    private String rowKey;

    @FieldMeta(name = "默认展开", showOrder = 117)
    @TableField("default_expand_all")
    private YesNo defaultExpandAll;

    @FieldMeta(name = "树属性", showOrder = 118)
    @TableField("tree_props")
    private String treeProps;

    @FieldMeta(name = "启用查询模式", showOrder = 119)
    @TableField("query_mode")
    private YesNo queryMode;

    @FieldMeta(name = "显示语言", showOrder = 121)
    @TableField("show_lang")
    private String showLang;

    @FieldMeta(name = "启用合计", showOrder = 122)
    @TableField("show_summary")
    private YesNo showSummary;

    @FieldMeta(name = "查询栏拓展按钮", showOrder = 123, maxLength = 512)
    @TableField("query_bar_buttons")
    private String queryBarButtons;

    @FieldMeta(name = "操作列拓展按钮", showOrder = 124, maxLength = 512)
    @TableField("operation_buttons")
    private String operationButtons;
}
