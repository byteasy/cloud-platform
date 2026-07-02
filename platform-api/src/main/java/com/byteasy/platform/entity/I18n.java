package com.byteasy.platform.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.byteasy.annotations.FieldMeta;
import com.byteasy.entity.AuditEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 平台国际化翻译表
 * </p>
 *
 * @author devops
 * @since 2026-03-18
 */
@Getter
@Setter
@TableName("platform_group_i18n")
@FieldMeta(name = "I18n", description = "平台国际化翻译表")
public class I18n extends AuditEntity<Long> {

    @TableField("group_id")
    @FieldMeta(description = "应用编号", showOrder = 101, isRequire = true, externalClass = ResourceGroup.class)
    private Long groupId;

    @TableField("code")
    @FieldMeta(description = "编码", showOrder = 102, isSearch = true, isRequire = true)
    private String code;

    @TableField("name")
    @FieldMeta(description = "显示名称", showOrder = 103, isSearch = true, isRequire = true, isI18n = true)
    private String name;
}
