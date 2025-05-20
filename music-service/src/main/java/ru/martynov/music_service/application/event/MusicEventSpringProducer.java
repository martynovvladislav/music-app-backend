package ru.martynov.music_service.application.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MusicEventSpringProducer {
    private final ApplicationEventPublisher publisher;

    public void publish(final Event event) {
        log.info("Publishing event: {}", event);
        publisher.publishEvent(event);
    }
}
