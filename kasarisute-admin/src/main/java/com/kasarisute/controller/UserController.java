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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kasarisute.common.LoginReq;
import com.kasarisute.common.ResponseData;
import com.kasarisute.common.UserInfomation;
import com.kasarisute.common.UserlogReqInfo;
import com.kasarisute.common.signInData;
import com.kasarisute.dataClass.UserClass;
import com.kasarisute.services.UserServices;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/user")
public class UserController {
        @Autowired
        private UserServices userServices;

        @PostMapping("/login")
        public ResponseEntity<?> userLoginStart(@RequestBody LoginReq loginReq, HttpServletRequest request,
                        HttpServletResponse response) throws RuntimeException {

                HttpHeaders responseHeaders = new HttpHeaders();
                return new ResponseEntity<ResponseData<UserlogReqInfo>>(
                                userServices.userLogin(loginReq, request, response),
                                responseHeaders,
                                HttpStatus.OK);
        }

        @PostMapping("/signin")
        public ResponseEntity<?> userSignStart(@RequestBody signInData signInData) throws RuntimeException {
                HttpHeaders responseHeaders = new HttpHeaders();
                return new ResponseEntity<ResponseData<UserlogReqInfo>>(userServices.userSignin(signInData),
                                responseHeaders,
                                HttpStatus.OK);
        }

        @GetMapping("")
        public ResponseEntity<ResponseData<UserInfomation<UserClass>>> getUserSelf(HttpServletRequest request)
                        throws RuntimeException {

                Long uid = (Long) request.getAttribute("uid");
                HttpHeaders responseHttpHeaders = new HttpHeaders();
                return new ResponseEntity<ResponseData<UserInfomation<UserClass>>>(
                                userServices.getUserInfo(uid),
                                responseHttpHeaders,
                                HttpStatus.OK);
        }

        @GetMapping(value = "/{userId}")
        public ResponseEntity<ResponseData<UserInfomation<UserClass>>> getUserInfo(
                        @PathVariable("userId") Long userId) {
                HttpHeaders responseHttpHeaders = new HttpHeaders();
                return new ResponseEntity<ResponseData<UserInfomation<UserClass>>>(
                                userServices.getUserInfo(userId),
                                responseHttpHeaders,
                                HttpStatus.OK);
        }

        @GetMapping(value = "/check")
        public ResponseEntity<?> requestMethodName(@RequestParam String param) {
                System.out.println("/check");
                HttpHeaders responseHttpHeaders = new HttpHeaders();

            return new ResponseEntity<>("{}",responseHttpHeaders,HttpStatus.OK);
        }
        

        @GetMapping("/online")
        public ResponseEntity<?> getMethodName() {
                HttpHeaders responseHttpHeaders = new HttpHeaders();
                return new ResponseEntity<>(
                                "{}",
                                responseHttpHeaders,
                                HttpStatus.OK);
        }

}
