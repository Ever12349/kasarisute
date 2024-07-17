package com.kasarisute.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kasarisute.common.LoginReq;
import com.kasarisute.common.ResponseData;
import com.kasarisute.common.UserInfomation;
import com.kasarisute.common.UserlogReqInfo;
import com.kasarisute.common.signInData;
import com.kasarisute.dataClass.UserClass;
import com.kasarisute.repositories.UserRepository;
import com.kasarisute.services.UserServices;
import com.kasarisute.utils.JwtUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServices userServices;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtils jwtUtils;

    private SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();
    private final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder
            .getContextHolderStrategy();

    @PostMapping("/login")
    public ResponseEntity<?> userLoginStart(@RequestBody LoginReq loginReq, HttpServletRequest request,
            HttpServletResponse response) {

        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseData<UserlogReqInfo>>(
                userServices.userLogin(loginReq, request, response),
                responseHeaders,
                HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> userSignStart(@RequestBody signInData signInData) {
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseData<UserlogReqInfo>>(userServices.userSignin(signInData), responseHeaders,
                HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<ResponseData<UserInfomation<UserClass>>> getUserSelf(HttpServletRequest request) {

        Long uid = (Long) request.getAttribute("uid");

        HttpHeaders responseHttpHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseData<UserInfomation<UserClass>>>(
                userServices.getUserInfo(uid),
                responseHttpHeaders,
                HttpStatus.OK);
    }
    

    @GetMapping(value = "/{userId}")
    public ResponseEntity<ResponseData<UserInfomation<UserClass>>> getUserInfo(@PathVariable("userId") Long userId) {

        HttpHeaders responseHttpHeaders = new HttpHeaders();
        return new ResponseEntity<ResponseData<UserInfomation<UserClass>>>(
                userServices.getUserInfo(userId),
                responseHttpHeaders,
                HttpStatus.OK);
    }

}
