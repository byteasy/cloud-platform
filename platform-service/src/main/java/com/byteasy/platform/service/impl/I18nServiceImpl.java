package com.byteasy.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.byteasy.common.util.CastUtil;
import com.byteasy.platform.entity.I18n;
import com.byteasy.platform.mapper.I18nMapper;
import com.byteasy.platform.service.II18nService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 平台国际化翻译表 服务实现类
 * </p>
 *
 * @author devops
 * @since 2026-03-18
 */
@Service
public class I18nServiceImpl extends ServiceImpl<I18nMapper, I18n> implements II18nService {

    @Override
    public Map<String, String> selectByGroupId(Long groupId) {
        LambdaQueryWrapper<I18n> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(I18n::getGroupId, groupId);
        List<I18n> i18nList = list(queryWrapper);
        return CastUtil.listToMap(i18nList, I18n::getCode, I18n::getName);
    }
}
