package com.kasarisute.services;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.kasarisute.Application;
import com.kasarisute.sequenceGenerator.annotations.SnowFlakeGenerator;

@SpringBootTest
@ContextConfiguration(classes = Application.class)
public class UserServicesImpTest {

    @Test
    void testGenerateJwtReturnUser() {
        Class<?> class01 = SnowFlakeGenerator.class;
        System.out.println(">>>>>>>>>" + class01);
    }

    @Test
    void testGetUserInfo() {
    }

    @Test
    void testUserLogin() {

    }

    @Test
    void testUserSignin() {

    }
}
