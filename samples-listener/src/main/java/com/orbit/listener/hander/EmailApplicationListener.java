package com.orbit.listener.hander;

import com.orbit.listener.event.MyEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class EmailApplicationListener implements ApplicationListener<MyEvent> {
        private static final Logger log = LoggerFactory.getLogger(EmailApplicationListener.class);
        @Override
        public void onApplicationEvent(MyEvent event) {
            log.info("发送邮件");
        }
    }
