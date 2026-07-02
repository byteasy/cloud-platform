package com.byteasy.platform.dto.keycloak;

import lombok.Data;

import java.util.Map;

@Data
public class UserProfileAttributeMetadataDTO {

    private String name;
    private String displayName;
    private Boolean required;
    private Boolean readOnly;
    private Map<String, ?> annotations;
    private Map<String, ?> validators;
    private String group;
    private Boolean multivalued;
}
