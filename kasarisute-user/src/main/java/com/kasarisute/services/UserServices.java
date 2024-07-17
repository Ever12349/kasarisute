package com.kasarisute.services;

import com.kasarisute.common.LoginReq;
import com.kasarisute.common.ResponseData;
import com.kasarisute.common.UserInfomation;
import com.kasarisute.common.UserlogReqInfo;
import com.kasarisute.common.signInData;
import com.kasarisute.dataClass.UserClass;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface UserServices {
   ResponseData<UserlogReqInfo> userSignin(signInData signInData);

   ResponseData<UserlogReqInfo> userLogin(LoginReq loginReq, HttpServletRequest request, HttpServletResponse response);

   ResponseData<UserInfomation<UserClass>> getUserInfo(Long userId);
}