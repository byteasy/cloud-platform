package com.byteasy.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.byteasy.common.event.EventPublishUtil;
import com.byteasy.common.token.TokenUtil;
import com.byteasy.common.token.UserInfo;
import com.byteasy.event.ServiceInstanceEvent;
import com.byteasy.event.ServiceInstanceSource;
import com.byteasy.platform.entity.ServiceInstance;
import com.byteasy.platform.mapper.ServiceInstanceMapper;
import com.byteasy.platform.service.IServiceInstanceService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 平台服务定义表 服务实现类
 * </p>
 *
 * @author devops
 * @since 2026-03-03
 */
@Service
public class ServiceInstanceServiceImpl extends ServiceImpl<ServiceInstanceMapper, ServiceInstance> implements IServiceInstanceService {

    @Override
    public boolean saveOrUpdate(ServiceInstance entity) {
        UserInfo userInfo = TokenUtil.getUserinfo();
        if (userInfo.isStartedByUser()) {
            EventPublishUtil.publishEvent(new ServiceInstanceEvent(new ServiceInstanceSource(entity.getCode())));
            return super.saveOrUpdate(entity);
        } else {
            LambdaQueryWrapper<ServiceInstance> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(ServiceInstance::getCode, entity.getCode());
            ServiceInstance serviceInstance = getOne(queryWrapper);
            if (serviceInstance == null) {
                return super.saveOrUpdate(entity);
            }
            return true;
        }
    }

    @Override
    public ServiceInstance findServiceInstanceByCode(String code) {
        LambdaQueryWrapper<ServiceInstance> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ServiceInstance::getCode, code);
        return getOne(queryWrapper, false);
    }
}
