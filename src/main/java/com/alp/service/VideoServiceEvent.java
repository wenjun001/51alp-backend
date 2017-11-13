package com.alp.service;

import org.springframework.context.ApplicationEvent;

/**
 * This is an optional class used in publishing application events.
 * This can be used to inject events into the Spring Boot audit management endpoint.
 */
public class VideoServiceEvent extends ApplicationEvent {

    public VideoServiceEvent(Object source) {
        super(source);
    }

    public String toString() {
        return "My VideoService Event";
    }
}