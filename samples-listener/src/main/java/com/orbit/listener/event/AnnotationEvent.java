package com.orbit.listener.event;

import org.springframework.context.ApplicationEvent;

public class AnnotationEvent  extends ApplicationEvent {
    public AnnotationEvent(Object source) {
        super(source);
    }
}
