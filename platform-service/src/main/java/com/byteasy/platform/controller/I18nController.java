package com.byteasy.platform.controller;

import com.byteasy.Constant;
import com.byteasy.Result;
import com.byteasy.controller.AuditController;
import com.byteasy.platform.entity.I18n;
import com.byteasy.platform.service.II18nService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 平台国际化翻译表 前端控制器
 * </p>
 *
 * @author devops
 * @since 2026-03-18
 */
@RestController
@RequestMapping(Constant.SAPI + "/i18ns")
public class I18nController extends AuditController<Long, II18nService, I18n> {

    @GetMapping("/groups/{groupId}")
    public Result<Map<String, String>> selectByGroupId(@PathVariable("groupId") Long groupId) {
        return Result.success(baseService.selectByGroupId(groupId));
    }
}
