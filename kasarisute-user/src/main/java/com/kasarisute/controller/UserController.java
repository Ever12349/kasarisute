package com.kasarisute.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kasarisute.common.LoginReq;
import com.kasarisute.common.ResponseData;
import com.kasarisute.common.UserInfomation;
import com.kasarisute.common.signInData;
import com.kasarisute.services.UserServices;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServices userServices;

    @PostMapping("/login")
    public ResponseEntity<?> userLoginStart(@RequestBody LoginReq loginReq) {
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseData<UserInfomation>>(userServices.userLogin(loginReq), responseHeaders,
                HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> userSignStart(@RequestBody signInData signInData) {
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseData<UserInfomation>>(userServices.userSignin(signInData), responseHeaders,
                HttpStatus.OK);
    }

    @GetMapping(name = "/{userId}")
    public ResponseEntity<ResponseData<UserInfomation>> getUserInfo(@PathVariable Long userId) {

        HttpHeaders responseHttpHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseData<UserInfomation>>(userServices.getUserInfo(userId), responseHttpHeaders,
                HttpStatus.OK);
    }

    // @GetMapping("/{id}")
    // public ResponseEntity<?> getMethodName(@PathVariable String id) {

    // ResponseData reqData = new ResponseData();
    // reqData.setData(id);
    // return ResponseEntity.ok().body(id);
    // }
}
