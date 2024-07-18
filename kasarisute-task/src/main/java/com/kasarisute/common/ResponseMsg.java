package com.kasarisute.common;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class ResponseMsg {
    public static final String Ok = "操作成功";
    public static final String USER_IS_EXISTS = "该用户已经存在";
    public static final String USER_IS_NOT_EXISTS = "账号不存在,请检查是否输入错误";
    public static final String USER_IS_NULL = "无法获取对应的用户信息";
    public static final String DATA_ERROR = "数据异常";
    public static final String PASSWORD_ERROR = "密码或者用户错误";
}
