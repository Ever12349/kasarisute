package com.kasarisute.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

import com.kasarisute.KasarisuteUserApplication;
import com.kasarisute.common.LoginReq;
import com.kasarisute.common.ResponseData;
import com.kasarisute.common.UserInfomation;
import com.kasarisute.common.signInData;
import com.kasarisute.dataClass.UserClass;

@SpringBootTest(classes = KasarisuteUserApplication.class)
public class UserControllerTest {
    @Autowired
    UserController userController;

    @Test
    void testGetUserInfo() {
        Long userId = 7210138821663531008L;
        ResponseEntity<ResponseData<UserInfomation<UserClass>>> userInfo = userController.getUserInfo(userId);
        ;

        Assert.isInstanceOf(ResponseEntity.class, userInfo);

    }

    @Test
    void testUserLoginStart() {
        LoginReq loginReq = new LoginReq();
        loginReq.setUserIdentitiy("qianxiao0762@outlook.com");
        loginReq.setPassword("123456789");

        // ResponseEntity<?> userLoginStart = userController.userLoginStart(loginReq);

        // System.out.println(userLoginStart);
    }

    @Test
    void testUserSignStart() {
        signInData signInData = new signInData();

        signInData.setMail("qianxiao0762@outlook.com");
        signInData.setPassword("123456789");
        signInData.setUserName("QianXiao");

        ResponseEntity<?> userSignStart = userController.userSignStart(signInData);

        System.out.println(userSignStart);

    }
}
