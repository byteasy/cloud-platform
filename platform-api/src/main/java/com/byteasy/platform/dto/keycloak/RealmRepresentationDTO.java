package com.byteasy.platform.dto.keycloak;

import lombok.Data;

@Data
public class RealmRepresentationDTO {

    private String id;
    private String realm;
    private String displayName;
    private String displayNameHtml;
}
