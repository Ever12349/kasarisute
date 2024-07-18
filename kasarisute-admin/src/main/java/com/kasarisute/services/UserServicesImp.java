package com.kasarisute.services;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kasarisute.common.LoginReq;
import com.kasarisute.common.ResponseCode;
import com.kasarisute.common.ResponseData;
import com.kasarisute.common.ResponseMsg;
import com.kasarisute.common.UserInfomation;
import com.kasarisute.common.UserlogReqInfo;
import com.kasarisute.common.signInData;
import com.kasarisute.dataClass.UserClass;
import com.kasarisute.entitys.User;
import com.kasarisute.entitys.UserJwt;
import com.kasarisute.entitys.UserPw;
import com.kasarisute.repositories.UserJwtRepository;
import com.kasarisute.repositories.UserRepository;
import com.kasarisute.utils.JwtUtils;
import com.kasarisute.utils.UserUtils;

import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class UserServicesImp implements UserServices {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserJwtRepository userJwtRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;
    private SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();
    private final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder
            .getContextHolderStrategy();

    //
    public UserlogReqInfo generateJwtReturnUser(User user) {
        UserlogReqInfo userloginReqInfo = new UserlogReqInfo();

        UserClass userClass = new UserClass();
        BeanUtils.copyProperties(user, userClass);

        String jwt = jwtUtils.generateToken(new HashMap<>() {
            {
                put("userName", user.getUserName());
                put("uid", user.getUid());
                put("userCode", user.getUserCode());
                put("roles", user.getRoles());
            }
        });

        UserJwt userJwt = new UserJwt();
        userJwt.setJwt(jwt);
        userJwt.setUserCode(user.getUserCode());

        userJwtRepository.save(userJwt);

        userloginReqInfo.setUserInfo(userClass);
        userloginReqInfo.setAuthorization(jwt);

        return userloginReqInfo;
    }

    @Override
    @Transactional
    public ResponseData<UserlogReqInfo> userSignin(signInData signInData) {
        ResponseData<UserlogReqInfo> res = new ResponseData<>();

        String mail = signInData.getMail();
        String userName = signInData.getUserName();
        String password = signInData.getPassword();
        User user;
        UserPw userPw = new UserPw();
        userPw.setPassword(password);
        userPw.setPwIsCrypt(false);

        user = userRepository.findByMail(mail);
        if (user == null) {
            // 用户不存在
            user = new User();
            user.setMail(mail);
            user.setUserName(userName);
            user.setUserPw(userPw);

            // 对密码加密
            if (!userPw.isPwIsCrypt()) {
                userPw.setPassword(UserUtils.pwBCryptEncode(userPw.getPassword()));
                userPw.setPwIsCrypt(true);
            }

            // 生成userCode
            Long newUserCode = UserUtils.generateUserCode();
            user.setUserCode(newUserCode);
            userPw.setUserCode(newUserCode);

            user = userRepository.save(user);

            UserlogReqInfo jwtReturnUser = generateJwtReturnUser(user);

            res.setData(jwtReturnUser);
        } else {
            // 用户已经存在
            res.setCode(ResponseCode.ERROR);
            res.setMsg(ResponseMsg.USER_IS_EXISTS);
            res.setSuccess(false);
        }

        return res;
    }

    @Transactional
    @Override
    public ResponseData<UserlogReqInfo> userLogin(LoginReq loginReq, HttpServletRequest request,
            HttpServletResponse response) throws RuntimeException {
        ResponseData<UserlogReqInfo> res = new ResponseData<>();

        String userIdentitiy = loginReq.getUserIdentitiy();
        String password = loginReq.getPassword();

        Optional<User> userOptional = userRepository.findOne((root, query, cb) -> {
            Path<String> userName = root.get("userName");
            Path<String> mail = root.get("mail");
            Predicate predicate = cb.or(cb.equal(userName, userIdentitiy), cb.equal(mail, userIdentitiy));
            return predicate;
        });

        if (userOptional.isPresent()) {
            // 用户存在
            User user = userOptional.get();
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUid(),
                    password);
            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContext context = this.securityContextHolderStrategy.createEmptyContext();
            context.setAuthentication(authentication);
            this.securityContextRepository.saveContext(context, request, response);

            UserlogReqInfo jwtReturnUser = generateJwtReturnUser(user);
            ;
            res.setData(jwtReturnUser);

        } else {
            // 用户不存在
            res.setCode(ResponseCode.ERROR);
            res.setData(null);
            res.setMsg(ResponseMsg.USER_IS_NOT_EXISTS);
            res.setSuccess(false);

        }

        return res;
    }

    @Override
    @Transactional
    public ResponseData<UserInfomation<UserClass>> getUserInfo(Long userId) {
        ResponseData<UserInfomation<UserClass>> res = new ResponseData<>();

        User user = userRepository.findByUid(userId);

        if (user != null) {

            UserClass userClass = new UserClass();

            BeanUtils.copyProperties(user, userClass);

            UserInfomation<UserClass> userInfomation = new UserInfomation<>();
            userInfomation.setUserInfo(userClass);
            res.setData(userInfomation);
        } else {
            res.setData(null);
            res.setCode(ResponseCode.ERROR);
            res.setMsg(ResponseMsg.USER_IS_NULL);
            res.setSuccess(false);
        }

        return res;
    }

}