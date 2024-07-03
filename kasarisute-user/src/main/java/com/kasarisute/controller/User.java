package com.kasarisute.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class User {
    @GetMapping("/{id}")
    public String getMethodName() {
        
        
        return new String("hello spring boot");
    }
}
