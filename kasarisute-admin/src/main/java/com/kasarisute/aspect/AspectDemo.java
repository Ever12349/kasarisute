package com.kasarisute.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

// @Component
// @Aspect
public class AspectDemo {
    
    @Pointcut("execution(public * com.kasarisute.services.UserServices.getUserInfo(Long))")
    private void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        System.out.println("dobefo++++++MM________>>>>>>");
    }

}
