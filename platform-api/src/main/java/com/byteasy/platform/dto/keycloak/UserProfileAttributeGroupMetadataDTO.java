package com.byteasy.platform.dto.keycloak;

import lombok.Data;

import java.util.Map;

@Data
public class UserProfileAttributeGroupMetadataDTO {

    private String name;
    private String displayHeader;
    private String displayDescription;
    private Map<String, ?> annotations;
}
