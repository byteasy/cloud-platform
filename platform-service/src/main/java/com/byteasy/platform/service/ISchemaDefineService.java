package com.byteasy.platform.service;

import com.byteasy.dto.Column;
import com.byteasy.platform.entity.SchemaDefine;
import com.baomidou.mybatisplus.extension.service.IService;
import com.byteasy.platform.vo.SchemaClassTreeVO;

import java.util.List;

/**
 * <p>
 * 列配置表（存储业务实体字段的展示/校验/表单等配置） 服务类
 * </p>
 *
 * @author devops
 * @since 2026-03-13
 */
public interface ISchemaDefineService extends IService<SchemaDefine> {

    List<Column> queryByClassName(String className);

    void syncSchemaMetaInfo(String serviceName, String className, List<SchemaDefine> schemaDefines);

    List<SchemaClassTreeVO> listServicesClassTree();
}
