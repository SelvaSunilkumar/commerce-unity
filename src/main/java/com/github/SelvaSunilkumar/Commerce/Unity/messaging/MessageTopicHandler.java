package com.github.SelvaSunilkumar.Commerce.Unity.messaging;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.support.Acknowledgment;

public interface MessageTopicHandler {
    public void handleMessage(ConsumerRecord<String, Object> consumerRecord, Acknowledgment acknowledgment);
}
