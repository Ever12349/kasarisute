package com.kasarisute.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

public class TaskControllerTest {

    @Autowired
    private Environment environment;

    @Test
    void testGetMethodName() {

        // System.out.println(environment.getActiveProfiles());
    }

    @Test
    void testPostMethodName() {

    }
}
