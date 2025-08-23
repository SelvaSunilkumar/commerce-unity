package com.github.SelvaSunilkumar.Commerce.Unity.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class MessagingController {
    @Autowired
    MessageService messageService;

    @PostMapping("message/{topicId}")
    public void publishMessage(@PathVariable String topicId, @RequestBody Map<String, Object> message) {
        System.out.println("Publishing message to topic " + topicId);
        messageService.publishEventMessage(topicId, message);
    }
}
