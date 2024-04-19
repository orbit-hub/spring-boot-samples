package com.orbit.aop.aspect;

import com.orbit.aop.annotation.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;


import java.util.Arrays;

//@Aspect
//@Component
public class LogAspect2 {

    /**
     * 处理请求前执行
     */
    @Before(value = "@annotation(controllerLog)")
    public void boBefore(JoinPoint joinPoint, Log controllerLog) {
        System.out.println(controllerLog.title());
        String name = joinPoint.getSignature().getName();
        System.out.println("前置logStart()==>"+name+"....【args: "+ Arrays.asList(joinPoint.getArgs()) +"】");
    }

    /**
     * 处理完请求后执行
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "@annotation(controllerLog)", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, Log controllerLog, Object jsonResult) {
        System.out.println(controllerLog.title());
        String name = joinPoint.getSignature().getName();
        System.out.println("返回logReturn()==>"+name+"....【args: "+ Arrays.asList(joinPoint.getArgs()) +"】【result: "+jsonResult+"】");
    }
    //后置通知
    @After(value = "@annotation(controllerLog)")
    public void logEnd(JoinPoint joinPoint, Log controllerLog){
        System.out.println(controllerLog.title());
        String name = joinPoint.getSignature().getName();
        System.out.println("后置logEnd()==>"+name+"....【args: "+ Arrays.asList(joinPoint.getArgs()) +"】");
    }
    /**
     * 拦截异常操作
     *
     * @param joinPoint 切点
     * @param e 异常
     */
    @AfterThrowing(value = "@annotation(controllerLog)", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Log controllerLog, Exception e) {
        System.out.println(controllerLog.title());
        String name = joinPoint.getSignature().getName();
        System.out.println("异常logError()==>"+name+"....【args: "+ Arrays.asList(joinPoint.getArgs()) +"】【exception: "+e+"】");
    }

}
