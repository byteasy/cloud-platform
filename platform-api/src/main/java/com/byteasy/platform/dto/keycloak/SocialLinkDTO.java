package com.byteasy.platform.dto.keycloak;

import lombok.Data;

@Data
public class SocialLinkDTO {

    private String socialProvider;
    private String socialUserId;
    private String socialUsername;

}
