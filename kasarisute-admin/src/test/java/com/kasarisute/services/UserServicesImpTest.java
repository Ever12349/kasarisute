package com.kasarisute.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServicesImpTest {

    @Autowired
    private UserServices userServices;

    @Test
    void testGenerateJwtReturnUser() {

    }

    @Test
    void testGetUserInfo() {
        // ResponseData<UserInfomation<UserClass>> userInfo = userServices.getUserInfo(7211678087329751040L);
        
        // System.out.println(userInfo);
    }

    @Test
    void testUserLogin() {

    }

    @Test
    void testUserSignin() {

    }
}
