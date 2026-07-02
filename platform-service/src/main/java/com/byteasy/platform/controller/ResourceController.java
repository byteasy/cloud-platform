package com.byteasy.platform.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.byteasy.Constant;
import com.byteasy.Result;
import com.byteasy.annotations.Authentication;
import com.byteasy.common.util.RequestUtil;
import com.byteasy.controller.AuditController;
import com.byteasy.enums.YesNo;
import com.byteasy.platform.entity.Resource;
import com.byteasy.platform.service.IResourceService;
import com.byteasy.platform.service.IUserRoleService;
import com.byteasy.platform.vo.LeftMenuVO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 平台资源定义表（菜单/目录/按钮权限） 前端控制器
 * </p>
 *
 * @author devops
 * @since 2026-03-03
 */
@RestController
@RequestMapping(Constant.SAPI + "/resources")
public class ResourceController extends AuditController<Long, IResourceService, Resource> {

    @Autowired
    private IUserRoleService userRoleService;

    @Override
    public Result<Page<? extends Resource>> page() throws Exception {
        Long groupId = RequestUtil.getParameter("groupId", Long.class);
        List<LeftMenuVO> leftMenuVOS = baseService.selectByGroupId(groupId);
        Page<LeftMenuVO> page = new PageDTO<>();
        page.setRecords(leftMenuVOS);
        return Result.success(page);
    }

    @GetMapping("/leftMenu")
    @Operation(summary = "查询登录用户领域内的菜单")
    @Authentication
    public Result<Collection<LeftMenuVO>> selectByRouterPath(@RequestParam("fullPath") String fullPath) {
        return Result.success(userRoleService.selectByRouterPathAndLoginUser(fullPath));
    }

    @Override
    public Result<? extends Resource> saveOrUpdate(Resource resource) throws Exception {
        YesNo standButton = RequestUtil.getEnum("standButton", YesNo.class);
        if (YesNo.YES.equals(standButton)) {
            List<Resource> resources = new ArrayList<>();
            Resource view = new Resource();
            view.setType(Resource.Type.BUTTON);
            view.setCode(resource.getCode() + ".View");
            view.setGroupId(resource.getGroupId());
            view.setParentId(resource.getParentId());
            view.setName("{\"zh_CN\":\"查看\",\"en\":\"View\"}");
            view.setShowOrder(1);
            resources.add(view);

            Resource add = new Resource();
            add.setType(Resource.Type.BUTTON);
            add.setCode(resource.getCode() + ".Add");
            add.setGroupId(resource.getGroupId());
            add.setParentId(resource.getParentId());
            add.setName("{\"zh_CN\":\"添加\",\"en\":\"Add\"}");
            add.setShowOrder(2);
            resources.add(add);

            Resource edit = new Resource();
            edit.setType(Resource.Type.BUTTON);
            edit.setCode(resource.getCode() + ".Edit");
            edit.setGroupId(resource.getGroupId());
            edit.setParentId(resource.getParentId());
            edit.setName("{\"zh_CN\":\"编辑\",\"en\":\"Edit\"}");
            edit.setShowOrder(3);
            resources.add(edit);

            Resource delete = new Resource();
            delete.setType(Resource.Type.BUTTON);
            delete.setCode(resource.getCode() + ".Delete");
            delete.setGroupId(resource.getGroupId());
            delete.setParentId(resource.getParentId());
            delete.setName("{\"zh_CN\":\"删除\",\"en\":\"Delete\"}");
            delete.setShowOrder(4);
            resources.add(delete);

            Resource importBtn = new Resource();
            importBtn.setType(Resource.Type.BUTTON);
            importBtn.setCode(resource.getCode() + ".Import");
            importBtn.setGroupId(resource.getGroupId());
            importBtn.setParentId(resource.getParentId());
            importBtn.setName("{\"zh_CN\":\"导入\",\"en\":\"Import\"}");
            importBtn.setShowOrder(5);
            resources.add(importBtn);

            Resource export = new Resource();
            export.setType(Resource.Type.BUTTON);
            export.setCode(resource.getCode() + ".Export");
            export.setGroupId(resource.getGroupId());
            export.setParentId(resource.getParentId());
            export.setName("{\"zh_CN\":\"导出\",\"en\":\"Export\"}");
            export.setShowOrder(6);
            resources.add(export);

            for (Resource res : resources) {
                if (!baseService.hasCode(res.getCode())) {
                    baseService.save(res);
                }
            }
            return Result.success(resource);
        } else {
            return super.saveOrUpdate(resource);
        }
    }

    @GetMapping("/leftMenu/permissions")
    @Operation(summary = "查询当前领域用户拥有的所有按钮权限Code")
    @Authentication
    public Result<Collection<String>> selectButtonsByRouterPathAndLoginUser(@RequestParam("fullPath") String fullPath) {
        return Result.success(userRoleService.selectButtonsByRouterPathAndLoginUser(fullPath));
    }
}
