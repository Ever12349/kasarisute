package com.kasarisute.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kasarisute.entitys.Task;
import com.kasarisute.repositories.TaskRepositority;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskRepositority taskRepositority;
    @Autowired
    private Environment environment;


    @GetMapping("{id}")
    public String getMethodName(@PathVariable("id") String id) {
        String property = environment.getProperty("kasarisute.root");
        System.out.println(property);

        return new String(id);
    }

    @PostMapping("demo/{name}")
    public String postMethodName(@PathVariable("name") String name) {

        Task task = new Task();
        task.setTaskName(name);

        taskRepositority.save(task);
        return name;
    }

}
