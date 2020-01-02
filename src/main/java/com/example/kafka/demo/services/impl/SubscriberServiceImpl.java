package com.example.kafka.demo.services.impl;

import com.example.kafka.demo.models.Message;
import com.example.kafka.demo.services.SubscriberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;

@Service
public class SubscriberServiceImpl implements SubscriberService {
    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    @KafkaListener(topics = {"${spring.kafka.admin.topic-name}"},
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "kafkaListenerContainerFactory")
    public void listen(Message message) {
        log.info("Read message=['{}'] from topic.", message.getMessage());
    }
}
