package com.byteasy.platform.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.byteasy.annotations.FieldMeta;
import com.byteasy.entity.AuditEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 平台接口定义
 * </p>
 *
 * @author devops
 * @since 2026-05-11
 */
@Getter
@Setter
@TableName("platform_openapi")
@Schema(name = "Openapi", description = "平台接口定义")
public class Openapi extends AuditEntity<Long> {

    @Schema(description = "服务")
    @TableField("service_id")
    @FieldMeta(showOrder = 101, isRequire = true, isSearch = true, externalClass = ServiceInstance.class)
    private Long serviceId;

    @Schema(description = "接口名称")
    @TableField("name")
    @FieldMeta(showOrder = 102, isSearch = true, isRequire = true)
    private String name;

    @Schema(description = "Http方法")
    @TableField("http_method")
    @FieldMeta(showOrder = 103, isSearch = true, isRequire = true)
    private String httpMethod;

    @Schema(description = "类方法")
    @TableField("class_method")
    @FieldMeta(showOrder = 104, isSearch = true, isRequire = true)
    private String classMethod;

    @Schema(description = "URI")
    @TableField("uri")
    @FieldMeta(showOrder = 105, isSearch = true, isRequire = true)
    private String uri;

    @Schema(description = "说明")
    @TableField("remark")
    @FieldMeta(showOrder = 106, isShow = false)
    private String remark;

}
