package com.kasarisute.services;

import com.kasarisute.common.ResponseData;
import com.kasarisute.domain.User;

public interface UserServices {
   ResponseData userSignin(User userInfo);
}