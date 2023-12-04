package com.orbit.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import java.util.Arrays;

//@Aspect //说明这是切面
//@Component //切面也是容器中的组件
public class LogAspect {

    //前置通知  增强方法/增强器
    @Before("execution(* com.orbit.aop.service.HelloService.sayHello(..))")
    public void logStart(JoinPoint joinPoint){
        String name = joinPoint.getSignature().getName();
        System.out.println("前置logStart()==>"+name+"....【args: "+ Arrays.asList(joinPoint.getArgs()) +"】");
    }

    //返回通知
    @AfterReturning(value = "execution(* com.orbit.aop.service.HelloService.sayHello(..))",returning = "result")
    public void logReturn(JoinPoint joinPoint,Object result){
        String name = joinPoint.getSignature().getName();
        System.out.println("返回logReturn()==>"+name+"....【args: "+ Arrays.asList(joinPoint.getArgs()) +"】【result: "+result+"】");
    }


    //后置通知
    @After("execution(* com.orbit.aop.service.HelloService.sayHello(..))")
    public void logEnd(JoinPoint joinPoint){
        String name = joinPoint.getSignature().getName();
        System.out.println("后置logEnd()==>"+name+"....【args: "+ Arrays.asList(joinPoint.getArgs()) +"】");
    }


    //异常
    @AfterThrowing(value = "execution(* com.orbit.aop.service.HelloService.sayHello(..))",throwing = "e")
    public void logError(JoinPoint joinPoint,Exception e){
        String name = joinPoint.getSignature().getName();
        System.out.println("异常logError()==>"+name+"....【args: "+ Arrays.asList(joinPoint.getArgs()) +"】【exception: "+e+"】");
    }
}
