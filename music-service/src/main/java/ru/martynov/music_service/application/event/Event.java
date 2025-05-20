package ru.martynov.music_service.application.event;

import org.springframework.context.ApplicationEvent;

public abstract class Event extends ApplicationEvent {
    public Event(Object source) {
        super(source);
    }

    public String getType() {
        return "BASE_EVENT";
    }
}
