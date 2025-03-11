package com.kasarisute.utils;

import com.kasarisute.common.RequestData;

import jakarta.servlet.http.HttpServletRequest;

public abstract class CommonUtils {
    public static <T> RequestData<T> handlerRequestData(T entity, HttpServletRequest request) {
        return new RequestData<T>() {
            {
                setReqData(entity);
                setUid((Long) request.getAttribute("uid"));
                setUserCode((Long) request.getAttribute("userCode"));
                setUserName((String) request.getAttribute("userName"));
                setRoles((String) request.getAttribute("roles"));
            }
        };
    }
}
