package com.kasarisute.common;

import com.kasarisute.dataClass.UserClass;

public class UserlogReqInfo {
    UserClass userInfo;

    private String authorization;


    public UserlogReqInfo() {
    }

    public UserlogReqInfo(UserClass userInfo) {
        this.userInfo = userInfo;
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    public UserClass getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserClass userInfo) {
        this.userInfo = userInfo;
    }

    
}
