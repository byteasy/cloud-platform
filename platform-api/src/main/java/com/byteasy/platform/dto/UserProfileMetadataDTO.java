package com.byteasy.platform.dto;

import com.byteasy.platform.dto.keycloak.UserProfileAttributeGroupMetadataDTO;
import lombok.Data;

import java.util.List;

@Data
public class UserProfileMetadataDTO {

    private List<UserProfileAttributeGroupMetadataDTO> attributes;
    private List<UserProfileAttributeGroupMetadataDTO> groups;
}
