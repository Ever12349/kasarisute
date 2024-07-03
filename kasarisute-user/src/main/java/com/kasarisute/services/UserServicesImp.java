package com.kasarisute.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kasarisute.common.ResponseCode;
import com.kasarisute.common.ResponseData;
import com.kasarisute.common.ResponseMsg;
import com.kasarisute.domain.User;
import com.kasarisute.repositories.UserIdRepository;
import com.kasarisute.repositories.UserRepository;

@Service
public class UserServicesImp implements UserServices {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserIdRepository userIdRepository;

    @Override
    public ResponseData userSignin(User userInfo) {
        ResponseData req = new ResponseData();


        // userRepository
        // 判断账号是否存在
        boolean existsByMail = userRepository.existsByMail(userInfo.getMail());
        // 账户已经存在
        if(existsByMail) {
            req.setCode(ResponseCode.ERROR);
            req.setData(null);
            req.setMsg(ResponseMsg.USER_IS_EXISTS);
            req.setSuccess(false);
            return req;
        }
        // 账户不存在
        User save = userRepository.save(userInfo);
        req.setData(save);
        return req;
    }

}
