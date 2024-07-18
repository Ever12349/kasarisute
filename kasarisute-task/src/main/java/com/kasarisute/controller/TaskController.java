package com.kasarisute.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/task")
public class TaskController {
    @GetMapping("")
    public String getMethodName(@RequestParam String param) {
        return new String("123");
    }
}
