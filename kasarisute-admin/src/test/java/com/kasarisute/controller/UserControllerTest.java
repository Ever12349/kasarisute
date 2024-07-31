package com.kasarisute.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.kasarisute.Application;


@SpringBootTest
@ContextConfiguration(classes = Application.class)
public class UserControllerTest {
    @Autowired
    private UserController userController;
    @Test
    void testGetUserInfo() {

    }

    @Test
    void testGetUserSelf() {
    }

    @Test
    void testUserLoginStart() {

    }

    @Test
    void testUserSignStart() {

    }
}
