package com.byteasy.platform.dto.keycloak;

import lombok.Data;

import java.util.Map;

@Data
public class CredentialDTO {

    private String id;
    private String type;
    private String userLabel;
    private Long createdDate;
    private String secretData;
    private String credentialData;
    private Integer priority;
    private String value;
    private Boolean temporary;
    private String device;
    private String hashedSaltedValue;
    private String salt;
    private Integer hashIterations;
    private String algorithm;
    private String digits;
    private Integer period;
    private Map<String, ?> config;

}
