package com.kasarisute.dataClass;

import java.time.Instant;

import lombok.Data;

@Data
public class UserClass {
    private Long uid;
    private Long UserCode;
    private String userName;

    //用户的邮箱
    private String mail;
    //用户的权限 默认值为5
    private Integer permission = 5;
    //用户的角色
    private String roles = "ROLE_USER";

    //创建时间
    private Instant createTime;

    //"状态 100未验证,200正常,300异常,400已经注销"
    private Integer status = 100;

}
