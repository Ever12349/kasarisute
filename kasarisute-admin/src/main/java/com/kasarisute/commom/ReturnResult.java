package com.kasarisute.commom;

import lombok.Data;

@Data
public class ReturnResult {
    private Integer code;
    private Object data;
    private String msg;
    private boolean success;
}
