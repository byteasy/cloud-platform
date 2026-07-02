package com.byteasy.platform.dto;

import com.byteasy.platform.dto.keycloak.*;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class UserDTO {

    private String id;
    /**
     * 工号
     */
    private String username;
    /**
     * 姓
     */
    private String firstName;
    /**
     * 名
     */
    private String lastName;
    private String email;
    private Boolean emailVerified;
    /**
     * 手机号等其他信息，手机号的key为phone
     */
    private Map<String, ?> attributes;
    @Deprecated
    private UserProfileAttributeMetadataDTO userProfileMetadata;
    @Deprecated
    private String self;
    @Deprecated
    private String origin;
    private Long createdTimestamp;
    private Boolean enabled;
    @Deprecated
    private Boolean totp;
    @Deprecated
    private String federationLink;
    @Deprecated
    private String serviceAccountClientId;
    private List<CredentialDTO> credentials;
    private List<String> disableableCredentialTypes;
    private List<String> requiredActions;
    private List<FederatedIdentityDTO> federatedIdentities;
    @Deprecated
    private List<String> realmRoles;
    @Deprecated
    private List<Map<String, ?>> clientRoles;
    @Deprecated
    private List<UserConsentDTO> clientConsents;
    private Integer notBefore;
    @Deprecated
    private List<Map<String, ?>> applicationRoles;
    @Deprecated
    private List<SocialLinkDTO> socialLinks;
    @Deprecated
    private List<String> groups;
    @Deprecated
    private Map<String, Boolean> access;
}
