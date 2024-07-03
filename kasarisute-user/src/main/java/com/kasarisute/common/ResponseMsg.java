package com.kasarisute.common;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class ResponseMsg {
    public static final String Ok = "操作成功";
    public static final String USER_IS_EXISTS = "该用户已经存在";
}
