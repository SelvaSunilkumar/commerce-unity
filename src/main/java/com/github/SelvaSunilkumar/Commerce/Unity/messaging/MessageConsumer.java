package com.github.SelvaSunilkumar.Commerce.Unity.messaging;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {
    @Autowired
    ApplicationContext applicationContext;

    @KafkaListener(topicPattern = ".*", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeMessages(ConsumerRecord<String, Object> consumerRecord, Acknowledgment acknowledgment) {
        try {
            String topic = consumerRecord.topic();
            System.out.println("Message from the topic: " + topic);
            String beanName = topic + "EventConsumer";

            if (!applicationContext.containsBean(beanName)) {
                throw new NoSuchBeanDefinitionException("Bean is not available for this topic: " + topic);
            }

            MessageTopicHandler messageTopicHandler = (MessageTopicHandler) applicationContext.getBean(beanName);
            messageTopicHandler.handleMessage(consumerRecord, acknowledgment);
        } catch (NoSuchBeanDefinitionException noSuchBeanDefinitionException) {
            System.out.println("No bean found for the topic: " + consumerRecord.topic());
        } catch (Exception e) {
            System.out.println("Error occurred in message consumption" + e);
        }
    }
}
