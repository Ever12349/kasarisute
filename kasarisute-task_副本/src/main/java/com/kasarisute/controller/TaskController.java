package com.kasarisute.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
public class TaskController {
    @GetMapping("{id}")
    public String getMethodName(@PathVariable("id") String id) {
        return new String(id);
    }
}
