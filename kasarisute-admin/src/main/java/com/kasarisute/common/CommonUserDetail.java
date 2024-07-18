package com.kasarisute.common;


import com.kasarisute.entitys.User;

import lombok.Data;

@Data
public class CommonUserDetail {
   private Long uid;
   private Long userCode;
    private String userName;
    private String passwordUnencrypted;//未加密的密码
    private String passwordEncrypted;//加密的密码
    private String roles;
    private String mail;
    private Integer permission = 5;


    private User user;
}
