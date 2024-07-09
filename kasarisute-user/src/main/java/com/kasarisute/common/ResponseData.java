package com.kasarisute.common;


import lombok.Data;

@Data
public class ResponseData<T> {
    private Integer code = ResponseCode.OK;
    private T data;
    private String msg = ResponseMsg.Ok;
    private boolean success=true;
}
