package com.kasarisute.common;

import lombok.Data;

@Data
public class RequestData<T> {
    private Long uid;
    private String userName;
    private Long userCode;
    private String roles;
    private T reqData;
}
