package com.orbit.listener.service;


import com.orbit.listener.event.AnnotationEvent;
import com.orbit.listener.event.MyEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component

public class MyService {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(MyService.class);

    @Autowired
    private ApplicationEventPublisher publisher; // applicationContext
    public void doBusiness() {

        log.info("主线业务");
//        publisher.publishEvent(new MyEvent("MyService.doBusiness()"));
        publisher.publishEvent(new AnnotationEvent("MyService.doBusiness()"));

//        log.info("发送短信");
//        log.info("发送邮件");

    }
}
