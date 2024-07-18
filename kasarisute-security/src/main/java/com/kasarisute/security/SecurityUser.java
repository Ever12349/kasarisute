package com.kasarisute.security;

import lombok.Data;

@Data
public class SecurityUser {
    private Long uid;
    private Long userCode;
    private String userName;
    private String roles;

    private Integer permission = 5;
    private String mail;

    private String password;
}
