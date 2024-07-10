package com.kasarisute.common;

import com.kasarisute.domain.User;

import lombok.Data;

@Data
public class UserInfomation {
    private User userInfo;

    public User getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(User userInfo) {
        this.userInfo = userInfo;
    }

    public UserInfomation() {}

    public UserInfomation(User userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public String toString() {
        return "UserInfomation [userInfo=" + userInfo + "]";
    }
    
}
