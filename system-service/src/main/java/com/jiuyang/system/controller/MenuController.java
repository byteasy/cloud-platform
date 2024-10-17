package com.jiuyang.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiuyang.common.Result;
import com.jiuyang.system.entity.Menu;
import com.jiuyang.system.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private IMenuService menuService;
    @GetMapping("/page")
    public Result<String> page() {
        String s = "";
        s = null;
        s = s.substring(5);
        return Result.of();
    }

    @GetMapping
    public Result<IPage<Menu>> list() {
        return Result.of(menuService.page(new Page<>(0, 20)));
    }
}
