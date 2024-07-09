package com.kasarisute.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kasarisute.common.LoginReq;
import com.kasarisute.common.ResponseCode;
import com.kasarisute.common.ResponseData;
import com.kasarisute.common.ResponseMsg;
import com.kasarisute.common.UserInfomation;
import com.kasarisute.common.signInData;
import com.kasarisute.domain.User;
import com.kasarisute.domain.UserPw;
import com.kasarisute.repositories.UserRepository;

import utils.UserUtils;

@Service
public class UserServicesImp implements UserServices {
    @Autowired
    private UserRepository userRepository;


    public User genreateUidAndUserCodeAndSaveUserInfo(User userInfo, ResponseData<UserInfomation> req) {
        User user = new User();
        UserPw userPw = userInfo.getUserPw();
        if(!userPw.isPwIsCrypt()) {
            userPw.setPassword(UserUtils.pwBCryptEncode(userPw.getPassword()));
            userPw.setPwIsCrypt(true);
        }
        if (userInfo.getUid() == null) {
            String newUid = UserUtils.generateUid();
            user = userRepository.findByUid(newUid);
            if (user == null) {
                userInfo.setUid(newUid);
                userPw.setUid(newUid);
                user = userRepository.saveAndFlush(userInfo);

                User ReqUserInfo = userRepository.findByUid(user.getUid());
                UserInfomation userInfomation = new UserInfomation(ReqUserInfo);
                req.setData(userInfomation);
            }
        }

        return user;
    }

    @Override
    @Transactional
    public ResponseData<UserInfomation> userSignin(signInData signInData) {
        ResponseData<UserInfomation> req = new ResponseData<>();

        String mail = signInData.getMail();
        String userName = signInData.getUserName();
        String password = signInData.getPassword();
        User userData;
        UserPw userPw = new UserPw();
        userPw.setPassword(password);
        userPw.setPwIsCrypt(false);

        userData = userRepository.findByMail(mail);
        if (userData == null) {
            // 用户不存在
            userData = new User();
            userData.setMail(mail);
            userData.setUserName(userName);
            userData.setUserPw(userPw);
            userData = genreateUidAndUserCodeAndSaveUserInfo(userData, req);
        } else {
            // 用户已经存在
            req.setCode(ResponseCode.ERROR);
            req.setMsg(ResponseMsg.USER_IS_EXISTS);
            req.setSuccess(false);
        }

        // userData = userRepository.findByMail(userInfo.getMail());
        // if(userData == null) {
        // // 用户不存在
        // userData = userRepository.save(userInfo);
        // genreateUidAndUserCodeAndSaveUserInfo(userData,req);
        // }else {
        // //用户存在
        // if(userData.getUserCode() == null || userData.getUserCode() == null) {
        // //没有生成userid与userCode
        // genreateUidAndUserCodeAndSaveUserInfo(userData,req);
        // }else {
        // req.setCode(ResponseCode.ERROR);
        // req.setData(null);
        // req.setMsg(ResponseMsg.USER_IS_EXISTS);
        // req.setSuccess(false);
        // }
        // }
        return req;
    }

    @Override
    public ResponseData<UserInfomation> userLogin(LoginReq loginReq) {
        ResponseData<UserInfomation> req = new ResponseData<>();

        // User userInfo = userRepository.findOne()

        return req;
    }
}