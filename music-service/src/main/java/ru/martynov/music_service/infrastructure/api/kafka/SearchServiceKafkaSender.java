package ru.martynov.music_service.infrastructure.api.kafka;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.martynov.music_service.application.event.Event;

@Service
@RequiredArgsConstructor
public class SearchServiceKafkaSender {
    private final KafkaTemplate<String, Event> kafkaTemplate;

    public void send(Event event) {
        ProducerRecord<String, Event> record = new ProducerRecord<>("track-events", event);
        record.headers().add("__TypeId__", event.getType().getBytes());

        kafkaTemplate.send(record);
    }
}
