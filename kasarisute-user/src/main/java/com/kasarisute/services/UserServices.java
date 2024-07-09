package com.kasarisute.services;

import com.kasarisute.common.LoginReq;
import com.kasarisute.common.ResponseData;
import com.kasarisute.common.UserInfomation;
import com.kasarisute.common.signInData;

public interface UserServices {
   ResponseData<UserInfomation> userSignin(signInData signInData);
   ResponseData<UserInfomation> userLogin(LoginReq loginReq);
}