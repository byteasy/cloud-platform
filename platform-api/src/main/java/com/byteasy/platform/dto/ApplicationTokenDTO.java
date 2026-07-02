package com.byteasy.platform.dto;

import lombok.Data;

@Data
public class ApplicationTokenDTO {

    /**
     * 系统分配的appId
     */
    private String clientId;
    /**
     * 系统分配的密钥
     */
    private String clientSecret;
    /**
     * 请求登录的系统密码
     */
    private String app;
    /**
     * 用户所属领域
     */
    private String realm;
    /**
     * 用户账户
     */
    private String username;
}
