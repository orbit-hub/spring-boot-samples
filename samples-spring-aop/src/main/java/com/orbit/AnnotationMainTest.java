package com.orbit;

import com.orbit.aop.config.MainConfig;
import com.orbit.aop.service.HelloService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnotationMainTest {

    public static void main(String[] args) {
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(MainConfig.class);

        HelloService helloService = applicationContext.getBean(HelloService.class);

        helloService.sayHello("zhangsan");

    }

}
