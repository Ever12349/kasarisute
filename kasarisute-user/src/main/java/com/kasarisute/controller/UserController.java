package com.kasarisute.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kasarisute.common.ResponseData;
import com.kasarisute.domain.User;
import com.kasarisute.services.UserServices;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServices userServices;

    @PostMapping("")
    public ResponseData userSignStart(@RequestBody User userInfo) {
        return userServices.userSignin(userInfo);
    }

    @GetMapping("/{id}")
    public ResponseData getMethodName(@PathVariable String id) {

        ResponseData reqData = new ResponseData();
        reqData.setData(id);
        return reqData;
    }
}
