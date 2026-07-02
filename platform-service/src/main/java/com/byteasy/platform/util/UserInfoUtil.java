package com.byteasy.platform.util;

import com.byteasy.common.token.UserInfo;
import com.byteasy.platform.dto.UserDTO;
import org.apache.catalina.Realm;
import org.apache.commons.lang3.StringUtils;

public final class UserInfoUtil {

    public static UserInfo convert(UserDTO userDTO, Realm realm) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(userDTO.getUsername());
        userInfo.setEmail(userDTO.getEmail());
        userInfo.setEmailVerified(userDTO.getEmailVerified() != null ? userDTO.getEmailVerified() : false);
        userInfo.setName(StringUtils.join(userDTO.getFirstName() + userDTO.getLastName()));
        userInfo.setGivenName(userDTO.getFirstName());
        userInfo.setFamilyName(userDTO.getLastName());
        userInfo.setUserId(userDTO.getId());
        userInfo.setMobile(String.valueOf(userDTO.getAttributes().get("phone")));
        return userInfo;
    }
}
