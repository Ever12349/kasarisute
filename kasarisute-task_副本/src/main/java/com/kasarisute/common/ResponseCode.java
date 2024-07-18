package com.kasarisute.common;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class ResponseCode {
    public static final Integer OK = 200;
    public static final Integer ERROR = 500;
}
