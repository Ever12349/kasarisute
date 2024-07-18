package com.kasarisute.common;

import com.kasarisute.dataClass.UserClass;

import lombok.Data;

@Data
public class UserInfomation<T extends UserClass> {
    private T userInfo;

    public T getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(T userInfo) {
        this.userInfo = userInfo;
    }

    public UserInfomation() {}

    public UserInfomation(T userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public String toString() {
        return "UserInfomation [userInfo=" + userInfo + "]";
    }
    
}
