package com.byteasy.platform.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class RoleUserDto {

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户登陆账号
     */
    private String userName;

    /**
     * 姓
     */
    private String firstName;

    /**
     * 名
     */
    private String lastName;

    /**
     * 领域
     */
    private String realm;

    /**
     * 是否有当前角色
     */
    private Boolean hasRole = false;
}
