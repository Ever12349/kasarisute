package com.kasarisute.common;


import lombok.Data;

@Data
public class ResponseData {
    private Integer code = ResponseCode.OK;
    private Object data;
    private String msg = ResponseMsg.Ok;
    private boolean success=true;
}
