package com.byteasy.platform.service.impl;

import com.byteasy.platform.entity.Openapi;
import com.byteasy.platform.mapper.OpenapiMapper;
import com.byteasy.platform.service.IOpenapiService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 平台OpenAPI接口定义表 服务实现类
 * </p>
 *
 * @author devops
 * @since 2026-03-03
 */
@Service
public class OpenapiServiceImpl extends ServiceImpl<OpenapiMapper, Openapi> implements IOpenapiService {

}
