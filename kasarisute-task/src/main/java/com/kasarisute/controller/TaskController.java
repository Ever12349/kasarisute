package com.kasarisute.controller;

import org.springframework.beans.factory.annotation.Autowired;
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


    @GetMapping("{id}")
    public String getMethodName(@PathVariable("id") String id) {

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
