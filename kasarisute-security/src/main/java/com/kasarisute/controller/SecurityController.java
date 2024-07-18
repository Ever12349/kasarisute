package com.kasarisute.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SecurityController {
    @GetMapping("/path")
    public String getMethodName() {
        return new String("hello world");
    }
    
}
