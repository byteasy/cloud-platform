package com.byteasy.platform.dto.keycloak;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AccessTokenDTO {

    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("expires_in")
    private Long expiresIn;
    @JsonProperty("refresh_expires_in")
    private Long refreshExpiresIn;
    @JsonProperty("refresh_token")
    private String refreshToken;
    @JsonProperty("token_type")
    private String tokenType;
    @JsonProperty("id_token")
    private String idToken;
    @JsonProperty("not-before-policy")
    private Integer notBeforePolicy;
    @JsonProperty("session_state")
    private String sessionState;
    private String scope;
}
