package com.kasarisute.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kasarisute.common.ResponseCode;
import com.kasarisute.common.ResponseData;

@RestControllerAdvice
public class UserResponseException {

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<?> resolveException(Exception ex) {
        HttpHeaders responseHeaders = new HttpHeaders();

        return new ResponseEntity<ResponseData<?>>(
                new ResponseData<>() {
                    {
                        setCode(ResponseCode.ERROR);
                        setSuccess(false);
                        setMsg(ex.getMessage());
                    }
                },
                responseHeaders,
                HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler({ BadCredentialsException.class })
    public ResponseEntity<?> resolveCredentialsException(Exception ex) {
        HttpHeaders responseHeaders = new HttpHeaders();

        return new ResponseEntity<ResponseData<?>>(
                new ResponseData<>() {
                    {
                        setCode(ResponseCode.ERROR);
                        setSuccess(false);
                        setMsg("用户名或者密码错误");
                    }
                },
                responseHeaders,
                HttpStatus.UNAUTHORIZED);
    }
}
