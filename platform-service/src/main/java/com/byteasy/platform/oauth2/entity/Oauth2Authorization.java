package com.byteasy.platform.oauth2.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.byteasy.annotations.FieldDisplay;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author devops
 * @since 2026-02-26
 */
@Getter
@Setter
@TableName("oauth2_authorization")
@Schema(name = "Oauth2Authorization", description = "")
public class Oauth2Authorization implements Serializable {

    @FieldDisplay(showOrder = 101, isOverflow = true)
    private String id;

    @FieldDisplay(showOrder = 102, isOverflow = true)
    private String registeredClientId;

    @FieldDisplay(showOrder = 103, isSearch = true)
    private String principalName;

    @FieldDisplay(showOrder = 104)
    private String authorizationGrantType;

    @FieldDisplay(showOrder = 105)
    private String authorizedScopes;

    @FieldDisplay(showOrder = 106, isOverflow = true)
    private String attributes;

    @FieldDisplay(showOrder = 107, isShow = false)
    private String state;

    @FieldDisplay(showOrder = 108, isShow = false)
    private String authorizationCodeValue;

    @FieldDisplay(showOrder = 109)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime authorizationCodeIssuedAt;

    @FieldDisplay(showOrder = 110)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime authorizationCodeExpiresAt;

    @FieldDisplay(showOrder = 111, isOverflow = true)
    private String authorizationCodeMetadata;

    @FieldDisplay(showOrder = 112, isOverflow = true)
    private String accessTokenValue;

    @FieldDisplay(showOrder = 113)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime accessTokenIssuedAt;

    @FieldDisplay(showOrder = 114)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime accessTokenExpiresAt;

    @FieldDisplay(showOrder = 115,  isOverflow = true)
    private String accessTokenMetadata;

    @FieldDisplay(showOrder = 116)
    private String accessTokenType;

    @FieldDisplay(showOrder = 117)
    private String accessTokenScopes;

    @FieldDisplay(showOrder = 118, isOverflow = true)
    private String oidcIdTokenValue;

    @FieldDisplay(showOrder = 119)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime oidcIdTokenIssuedAt;

    @FieldDisplay(showOrder = 120)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime oidcIdTokenExpiresAt;

    @FieldDisplay(showOrder = 121, isOverflow = true)
    private String oidcIdTokenMetadata;

    @FieldDisplay(showOrder = 122, isOverflow = true)
    private String refreshTokenValue;

    @FieldDisplay(showOrder = 123)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime refreshTokenIssuedAt;

    @FieldDisplay(showOrder = 124)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime refreshTokenExpiresAt;

    @FieldDisplay(showOrder = 125, isOverflow = true)
    private String refreshTokenMetadata;

    @FieldDisplay(showOrder = 126)
    private String userCodeValue;

    @FieldDisplay(showOrder = 127)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime userCodeIssuedAt;

    @FieldDisplay(showOrder = 128)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime userCodeExpiresAt;

    @FieldDisplay(showOrder = 129, isOverflow = true)
    private String userCodeMetadata;

    @FieldDisplay(showOrder = 130)
    private String deviceCodeValue;

    @FieldDisplay(showOrder = 131)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime deviceCodeIssuedAt;

    @FieldDisplay(showOrder = 132)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime deviceCodeExpiresAt;

    @FieldDisplay(showOrder = 130, isOverflow = true)
    private String deviceCodeMetadata;
}
