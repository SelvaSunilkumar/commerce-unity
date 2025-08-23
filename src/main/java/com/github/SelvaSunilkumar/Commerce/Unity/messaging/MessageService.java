package com.github.SelvaSunilkumar.Commerce.Unity.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    public MessageService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishEventMessage(String event, Object message) {
        kafkaTemplate.send(event, message);
    }
}
