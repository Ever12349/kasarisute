package com.kasarisute.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kasarisute.common.ResponseCode;
import com.kasarisute.common.ResponseData;
import com.kasarisute.common.ResponseMsg;
import com.kasarisute.common.UserInfomation;
import com.kasarisute.domain.User;
import com.kasarisute.domain.UserId;
import com.kasarisute.repositories.UserIdRepository;
import com.kasarisute.repositories.UserRepository;

import utils.UserUtils;

@Service
public class UserServicesImp implements UserServices {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserIdRepository userIdRepository;

    protected User genreateUidAndUserCodeAndSaveUserInfo(User userInfo, ResponseData req) {

        Optional<UserId> byId = userIdRepository.findById(userInfo.getId());

        if (!byId.isPresent()) {
            req.setCode(ResponseCode.ERROR);
            req.setData(null);
            req.setMsg(ResponseMsg.DATA_ERROR);
            req.setSuccess(false);

        }
        UserId userIdInfo = byId.get();

        userInfo.setUid(userIdInfo.getUid());
        userInfo.setUserCode(userIdInfo.getUserCode());
        userInfo.setPassword(UserUtils.sha256(userInfo.getPassword()));

        User saveUserInfo = userRepository.saveAndFlush(userInfo);

        return saveUserInfo;
    }

    @Override
    @Transactional
    public ResponseData userSignin(User userInfo) {
        ResponseData req = new ResponseData();

        // userRepository
        // 判断账号是否存在
        boolean existsByMail = userRepository.existsByMail(userInfo.getMail());
        // 账户已经存在
        if (existsByMail) {
            User userInfobyMail = userRepository.findByMail(userInfo.getMail());

            if (userInfobyMail.getUserCode() != null && userInfobyMail.getUid() != null) {
                req.setCode(ResponseCode.ERROR);
                req.setData(null);
                req.setMsg(ResponseMsg.USER_IS_EXISTS);
                req.setSuccess(false);
                return req;

            }
            User resInfo = genreateUidAndUserCodeAndSaveUserInfo(userInfobyMail, req);
            UserInfomation uInfomation = new UserInfomation(resInfo);
            req.setData(uInfomation);
            return req;
        }
        // 账户不存在
        User saveInfo = userRepository.save(userInfo);
        User resInfo = genreateUidAndUserCodeAndSaveUserInfo(saveInfo, req);
        UserInfomation uInfomation = new UserInfomation(resInfo);

        req.setData(uInfomation);
        return req;
    }

}