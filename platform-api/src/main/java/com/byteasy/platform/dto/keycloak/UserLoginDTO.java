package com.byteasy.platform.dto.keycloak;

import lombok.Data;

@Data
public class UserLoginDTO {

    private String realm;
    private String scope;
    private String clientId;
    private String clientSecret;
    private GrantType grantType;

    /**
     * grantType为password必填
     */
    private String username;
    private String password;
    /**
     * grantType为REFRESH_TOKEN必填
     */
    private String refreshToken;
}
