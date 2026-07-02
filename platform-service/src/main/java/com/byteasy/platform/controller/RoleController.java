package com.byteasy.platform.controller;

import com.byteasy.Constant;
import com.byteasy.Result;
import com.byteasy.annotations.Authentication;
import com.byteasy.controller.AuditController;
import com.byteasy.platform.entity.Role;
import com.byteasy.platform.service.IRoleResourceService;
import com.byteasy.platform.service.IRoleService;
import com.byteasy.platform.vo.RolePermissionVO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 平台角色定义表 前端控制器
 * </p>
 *
 * @author devops
 * @since 2026-04-02
 */
@RestController
@RequestMapping(Constant.SAPI + "/roles")
public class RoleController extends AuditController<Long, IRoleService, Role> {
    @Autowired
    private IRoleResourceService roleResourceService;

    @Operation(description = "查询所有可分配权限及角色拥有的权限")
    @GetMapping("/permissions")
    @Authentication
    public Result<RolePermissionVO> getPermission(@RequestParam("groupId") Long groupId, @RequestParam("roleId") Long roleId) {
        return Result.success(roleResourceService.getPermission(groupId, roleId));
    }

    @Operation(description = "修改角色权限")
    @PostMapping("/permissions")
    @Authentication
    public Result savePermission(@RequestBody RolePermissionVO rolePermissionVO) {
        roleResourceService.updatePermission(rolePermissionVO);
        return Result.success();
    }
}
