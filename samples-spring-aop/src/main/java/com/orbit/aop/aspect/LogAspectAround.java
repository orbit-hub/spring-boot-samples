package com.orbit.aop.aspect;


import com.orbit.aop.annotation.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LogAspectAround {



    @Around(value = "@annotation(controllerLog)")
    public Object doAfterThrowing(ProceedingJoinPoint joinPoint, Log controllerLog) {
        System.out.println(controllerLog.title());
        System.out.println(joinPoint.getArgs());
        System.out.println("环绕前置通知");
        Object proceed = null;
        try {
            proceed = joinPoint.proceed();
        } catch (Throwable e) {
            System.out.println("环绕异常通知");
            throw new RuntimeException(e);
        }finally {
            System.out.println("环绕最终通知");
        }
        System.out.println("环绕后置通知");
        return proceed;
    }
    /**
     * 11
     * [Ljava.lang.Object;@463fd068
     * 环绕前置通知
     * 你好：zhangsan
     * 环绕异常通知
     * 环绕最终通知
     */
}
