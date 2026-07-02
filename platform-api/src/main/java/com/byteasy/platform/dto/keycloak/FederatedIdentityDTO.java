package com.byteasy.platform.dto.keycloak;

import lombok.Data;

@Data
public class FederatedIdentityDTO {

    private String identityProvider;
    private String userId;
    private String userName;

}
