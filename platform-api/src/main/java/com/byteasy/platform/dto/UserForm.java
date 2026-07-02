package com.byteasy.platform.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserForm {

    private UserDTO user;
    private List<Long> roles;
    private String dept;
    private String realm;

}
