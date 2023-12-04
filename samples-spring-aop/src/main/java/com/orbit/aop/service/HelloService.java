package com.orbit.aop.service;

import com.orbit.aop.annotation.Log;
import org.springframework.stereotype.Component;

/**
 * @author h3365
 */
@Component
public class HelloService {

    @Log
    public String sayHello(String name){
        String result = "你好："+name;
        System.out.println(result);
        int length = name.length();
        return result + "---" + length;
    }
}
