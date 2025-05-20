
package ru.martynov.music_service.infrastructure.api.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import ru.martynov.music_service.application.event.Event;

@Component
@RequiredArgsConstructor
@Slf4j
public class EventListener {
    private final SearchServiceKafkaSender searchServiceKafkaSender;

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void handleEvent(Event event) {
        log.info("Sending kafka event to search service {}", event);
        searchServiceKafkaSender.send(event);
    }
}
