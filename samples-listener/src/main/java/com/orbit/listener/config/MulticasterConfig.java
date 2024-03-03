package com.orbit.listener.config;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.GenericApplicationListener;
import org.springframework.core.ResolvableType;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MulticasterConfig {
    @Bean
    public ApplicationEventMulticaster applicationEventMulticaster(ConfigurableApplicationContext context, ThreadPoolTaskExecutor executor) {
        return new AbstractApplicationEventMulticaster() {
            private List<GenericApplicationListener> listeners = new ArrayList<>();

            // 收集监听器
            public void addApplicationListenerBean(String name) {
                ApplicationListener listener = context.getBean(name, ApplicationListener.class);
                System.out.println(listener);
                // 获取该监听器支持的事件类型
                ResolvableType type = ResolvableType.forClass(listener.getClass()).getInterfaces()[0].getGeneric();
                System.out.println(type);

                // 将原始的 listener 封装为支持事件类型检查的 listener
                GenericApplicationListener genericApplicationListener = new GenericApplicationListener() {
                    // 是否支持某事件类型                真实的事件类型
                    public boolean supportsEventType(ResolvableType eventType) {
                        return type.isAssignableFrom(eventType);
                    }

                    public void onApplicationEvent(ApplicationEvent event) {
                        executor.submit(() -> listener.onApplicationEvent(event));
                    }
                };

                listeners.add(genericApplicationListener);
            }

            // 发布事件
            public void multicastEvent(ApplicationEvent event, ResolvableType eventType) {
                for (GenericApplicationListener listener : listeners) {
                    if (listener.supportsEventType(ResolvableType.forClass(event.getClass()))) {
                        listener.onApplicationEvent(event);
                    }
                }
            }
        };
    }
}
