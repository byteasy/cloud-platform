package com.byteasy.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.byteasy.platform.entity.I18n;

import java.util.Map;

/**
 * <p>
 * 平台国际化翻译表 服务类
 * </p>
 *
 * @author devops
 * @since 2026-03-18
 */
public interface II18nService extends IService<I18n> {

    Map<String, String> selectByGroupId(Long groupId);
}
