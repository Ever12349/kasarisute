package com.kasarisute.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/flux/demo")
public class DemoController {
    @GetMapping("")
    public String getMethodName() {
        return new String("hello flux");
    }
}
