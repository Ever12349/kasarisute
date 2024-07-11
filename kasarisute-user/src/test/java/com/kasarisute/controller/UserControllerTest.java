package com.kasarisute.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

import com.kasarisute.KasarisuteUserApplication;
import com.kasarisute.common.ResponseData;
import com.kasarisute.common.UserInfomation;
import com.kasarisute.common.signInData;

@SpringBootTest(classes = KasarisuteUserApplication.class)
public class UserControllerTest {
    @Autowired
    UserController userController;
    @Test
    void testGetUserInfo() {
        Long userId = 1720536827163256L;
        ResponseEntity<ResponseData<UserInfomation>> userInfo = userController.getUserInfo(userId);;
        Assert.isInstanceOf(ResponseEntity.class, userInfo);

    }

    @Test
    void testUserLoginStart() {
        signInData signInData = new signInData();
        
        signInData.setMail("qianxiao0762@outlook.com");
        signInData.setPassword("123456789");
        signInData.setUserName("QianXiao");

        ResponseEntity<?> userSignStart = userController.userSignStart(signInData);
        
       System.out.println(userSignStart);

    }

    @Test
    void testUserSignStart() {

    }
}
