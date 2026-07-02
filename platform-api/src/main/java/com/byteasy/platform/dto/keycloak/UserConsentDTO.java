package com.byteasy.platform.dto.keycloak;

import lombok.Data;

import java.util.List;

@Data
public class UserConsentDTO {

    private String clientId;
    private List<String> grantedClientScopes;

    private Long createdDate;
    private Long lastUpdatedDate;
    private List<String> grantedRealmRoles;
}
