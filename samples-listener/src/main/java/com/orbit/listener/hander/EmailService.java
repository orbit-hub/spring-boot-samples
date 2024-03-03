package com.orbit.listener.hander;

import com.orbit.listener.annotations.MyListener;
import com.orbit.listener.event.AnnotationEvent;
import com.orbit.listener.event.MyEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EmailService {
    private static final Logger log = LoggerFactory.getLogger(EmailService.class);
    @EventListener
    public void listener(AnnotationEvent annotationEvent) {
        log.info("发送邮件");
    }

    @MyListener
    public void myListener(AnnotationEvent annotationEvent) {
        log.info("MyListener---发送邮件");
    }
}
