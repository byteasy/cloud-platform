package com.byteasy.platform.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.byteasy.platform.entity.*;
import com.byteasy.platform.service.*;
import com.google.common.collect.Lists;
import com.byteasy.common.token.TokenUtil;
import com.byteasy.common.util.CastUtil;
import com.byteasy.enums.Enable;
import com.byteasy.enums.YesNo;
import com.byteasy.exception.BizException;
import com.byteasy.platform.mapper.UserRoleMapper;
import com.byteasy.platform.service.*;
import com.byteasy.platform.util.UserValidUtil;
import com.byteasy.platform.vo.LeftMenuVO;
import com.byteasy.platform.vo.ResourceCategoryVO;
import com.byteasy.platform.vo.UserRoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 平台角色授权表 服务实现类
 * </p>
 *
 * @author devops
 * @since 2026-04-02
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IResourceGroupService resourceGroupService;
    @Autowired
    private IResourceCategoryService resourceCategoryService;
    @Autowired
    private IResourceService resourceService;

    @Override
    public Set<Long> getUserRoleIds(Long userId) {
        return baseMapper.getUserRoleIdsByUserId(userId);
    }

    @Override
    public void removeByUserId(Long userId) {
        LambdaQueryWrapper<UserRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserRole::getUserId, userId);
        remove(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveUserRole(Long userId, Set<Long> roleIds) {
        List<UserRole> list = Lists.newArrayList();
        for (Long roleId : roleIds) {
            UserRole userRole = new UserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(roleId);
            list.add(userRole);
        }
        saveBatch(list);
    }

    @Override
    public UserRoleVO getUserRoleInfo(Long userId) {
        UserRoleVO userRoleVO = new UserRoleVO();
        userRoleVO.setUserId(userId);
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Role::getStatus, Enable.ENABLE);
        queryWrapper.orderByAsc(Role::getId);
        userRoleVO.setRoles(roleService.list(queryWrapper));
        userRoleVO.setRoleIds(getUserRoleIds(userId));
        return userRoleVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveUserRole(UserRoleVO userRoleVO) {
        if (userRoleVO.getUserId() == null) {
            throw new BizException("缺少用户信息");
        }
        removeByUserId(userRoleVO.getUserId());
        saveUserRole(userRoleVO.getUserId(), userRoleVO.getRoleIds());
    }

    @Override
    public Collection<LeftMenuVO> selectByRouterPathAndLoginUser(String routerPath) {
        User user = userService.getUserByUserId(TokenUtil.getUserId());
        if (user == null) {
            return Collections.emptyList();
        }
        Set<Long> roleIds = getUserRoleIds(user.getId());

        if (CollectionUtil.isEmpty(roleIds)) {
            return Collections.emptyList();
        }
        List<ResourceGroup> resourceGroups = resourceGroupService.selectByRoleIds(roleIds);
        if (CollectionUtil.isEmpty(resourceGroups)) {
            return Collections.emptyList();
        }
        /**
         * 自动修改ResourceGroup中的Router，这样即使同一个菜单下权限分配的不一样，也能自动匹配到用户拥有的第一个菜单项
         */
        Set<Long> groupIds = resourceGroups.stream().map(ResourceGroup::getId).collect(Collectors.toSet());
        List<Resource> resources = resourceService.selectByRoleIdsAndGroupIds(roleIds, groupIds);
        Map<Long, List<Resource>> groupResourceMap = resources.stream().collect(Collectors.groupingBy(Resource::getGroupId));

        for (ResourceGroup group : resourceGroups) {
            Optional<Resource> first = groupResourceMap.get(group.getId()).stream().filter(r -> YesNo.YES.equals(r.getVisible())).findFirst();
            first.ifPresent(resource -> group.setRouter(resource.getRouterUrl()));
        }

        // 取前两级
        String[] arrs = routerPath.split("/");
        if (arrs.length < 3) {
            throw new BizException("路由长度至少3级");
        }
        String rootPath = "/" + arrs[1] + "/" + arrs[2];
        for (ResourceGroup group : resourceGroups) {
            if (group.getRouter().startsWith(rootPath)) {

                List<Resource> list = resourceService.selectByRoleIdsAndGroupIds(roleIds, group.getId());

                List<LeftMenuVO> leftMenuVOS = CastUtil.copyList(list, LeftMenuVO.class);

                Map<Long, LeftMenuVO> map = CastUtil.listToMap(leftMenuVOS, LeftMenuVO::getId);

                List<LeftMenuVO> resultList = new ArrayList<>();
                for (LeftMenuVO leftMenuVO : leftMenuVOS) {
                    if (leftMenuVO.getParentId() == null) {
                        resultList.add(leftMenuVO);
                    } else {
                        LeftMenuVO parent = map.get(leftMenuVO.getParentId());
                        if (parent != null) {
                            if (parent.getChildrenList() == null) {
                                parent.setChildrenList(new ArrayList<>());
                                parent.getChildrenList().add(leftMenuVO);
                            } else {
                                parent.getChildrenList().add(leftMenuVO);
                            }
                        }
                    }
                }
                updateResourceDirRedirectUri(resultList, map);
                return resultList;
            }
        }
        return List.of();
    }

    @Override
    public Collection<String> selectButtonsByRouterPathAndLoginUser(String routerPath) {
        User user = userService.getUserByUserId(TokenUtil.getUserId());
        Set<Long> roleIds = getUserRoleIds(user.getId());

        List<ResourceGroup> resourceGroups = resourceGroupService.selectByRoleIds(roleIds);
        if (CollectionUtil.isEmpty(resourceGroups)) {
            return Collections.emptyList();
        }
        /**
         * 自动修改ResourceGroup中的Router，这样即使同一个菜单下权限分配的不一样，也能自动匹配到用户拥有的第一个菜单项
         */
        Set<Long> groupIds = resourceGroups.stream().map(ResourceGroup::getId).collect(Collectors.toSet());
        List<Resource> resources = resourceService.selectByRoleIdsAndGroupIds(roleIds, groupIds);
        Map<Long, List<Resource>> groupResourceMap = resources.stream().collect(Collectors.groupingBy(Resource::getGroupId));

        for (ResourceGroup group : resourceGroups) {
            Resource first = groupResourceMap.get(group.getId()).getFirst();
            if (first != null) {
                group.setRouter(first.getRouterUrl());
            }
        }

        // 取前两级
        String[] arrs = routerPath.split("/");
        if (arrs.length < 3) {
            throw new BizException("路由长度至少3级");
        }
        String rootPath = "/" + arrs[1] + "/" + arrs[2];
        for (ResourceGroup group : resourceGroups) {
            if (group.getRouter().startsWith(rootPath)) {
                List<Resource> list = resourceService.selectByRoleIdsAndGroupIds(roleIds, group.getId());
                return list.stream().filter(r -> Resource.Type.BUTTON.equals(r.getType())).map(Resource::getCode).collect(Collectors.toSet());
            }
        }
        return List.of();
    }

    /**
     * 更新文件夹路由的重定向属性，因为同一个文件夹内，如果是通过后台配置的重定向地址，可能某些账号没有访问权限，如果是这样，将重定向地址改为动态地址，默认指向文件夹内的第一个菜单或者下一级的文件夹
     *
     * @param leftMenuVOS
     */
    protected void updateResourceDirRedirectUri(Collection<LeftMenuVO> leftMenuVOS, Map<Long, LeftMenuVO> map) {
        if (leftMenuVOS == null) {
            return;
        }
        for (LeftMenuVO leftMenuVO : leftMenuVOS) {
            if (Resource.Type.DIRECTORY.equals(leftMenuVO.getType()) && leftMenuVO.getChildrenList() != null) {
                Optional<LeftMenuVO> first = leftMenuVO.getChildrenList().stream().filter(r -> YesNo.YES.equals(r.getVisible())).findFirst();
                first.ifPresent(leftMenu -> leftMenuVO.setRedirectUrl(getAbsolutePath(leftMenuVO, map) + "/" + leftMenu.getRouterUrl()));
            }
            updateResourceDirRedirectUri(leftMenuVO.getChildrenList(), map);
        }
    }

    private String getAbsolutePath(LeftMenuVO leftMenuVO, Map<Long, LeftMenuVO> map) {
        LeftMenuVO parent = map.get(leftMenuVO.getParentId());
        if (parent == null) {
            return leftMenuVO.getRouterUrl();
        } else {
            return getAbsolutePath(parent, map) + "/" + leftMenuVO.getRouterUrl();
        }
    }


    /**
     * 顶部导航菜单
     *
     * @return
     */
    @Override
    public List<ResourceCategoryVO> selectCategoryByLoginUser() {
        User user = userService.getUserByUserId(TokenUtil.getUserId());
        UserValidUtil.checkUser(user);
        Set<Long> roleIds = getUserRoleIds(user.getId());

        if (CollectionUtil.isEmpty(roleIds)) {
            return Collections.emptyList();
        }

        List<ResourceGroup> resourceGroups = resourceGroupService.selectByRoleIds(roleIds);
        if (CollectionUtil.isEmpty(resourceGroups)) {
            return Collections.emptyList();
        }
        /**
         * 自动修改ResourceGroup中的Router，这样即使同一个菜单下权限分配的不一样，也能自动匹配到用户拥有的第一个菜单项
         */
        Set<Long> groupIds = resourceGroups.stream().map(ResourceGroup::getId).collect(Collectors.toSet());
        List<Resource> resources = resourceService.selectByRoleIdsAndGroupIds(roleIds, groupIds);
        Map<Long, List<Resource>> groupResourceMap = resources.stream().collect(Collectors.groupingBy(Resource::getGroupId));

        /**
         * group菜单改为菜单配置中设置为可见的第一个菜单
         */
        for (ResourceGroup group : resourceGroups) {
            Optional<Resource> first = groupResourceMap.get(group.getId()).stream().filter(r -> YesNo.YES.equals(r.getVisible())).findFirst();
            first.ifPresent(resource -> group.setRouter(resource.getRouterUrl()));
        }

        LambdaQueryWrapper<ResourceCategory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(ResourceCategory::getId, resourceGroups.stream().map(ResourceGroup::getCategoryId).collect(Collectors.toSet()));
        queryWrapper.orderByAsc(ResourceCategory::getShowOrder);

        List<ResourceCategory> list = resourceCategoryService.list(queryWrapper);

        Set<Long> parentIds = list.stream().map(ResourceCategory::getParentId).collect(Collectors.toSet());
        LambdaQueryWrapper<ResourceCategory> queryWrapper2 = new LambdaQueryWrapper<>();
        queryWrapper2.in(ResourceCategory::getId, parentIds);
        queryWrapper2.orderByAsc(ResourceCategory::getShowOrder);

        list.addAll(resourceCategoryService.list(queryWrapper2));

        List<ResourceCategoryVO> resourceCategoryVOList = CastUtil.copyList(list, ResourceCategoryVO.class);
        List<ResourceCategoryVO> resultList = Lists.newArrayList();

        Map<Long, ResourceCategoryVO> treeMap = CastUtil.listToMap(resourceCategoryVOList, ResourceCategoryVO::getId);
        for (ResourceCategoryVO resourceCategoryVO : resourceCategoryVOList) {
            if (resourceCategoryVO.getParentId() == null) {
                resultList.add(resourceCategoryVO);
            } else {
                ResourceCategoryVO parent = treeMap.get(resourceCategoryVO.getParentId());
                if (parent.getChildren() == null) {
                    parent.setChildren(new ArrayList<>());
                }
                parent.getChildren().add(resourceCategoryVO);
            }
        }

        Map<Long, List<ResourceGroup>> map = resourceGroups.stream().collect(Collectors.groupingBy(ResourceGroup::getCategoryId));

        for (Map.Entry<Long, List<ResourceGroup>> entry : map.entrySet()) {
            ResourceCategoryVO parent = treeMap.get(entry.getKey());
            parent.setResourceGroups(entry.getValue());
        }
        return resultList;
    }
}
