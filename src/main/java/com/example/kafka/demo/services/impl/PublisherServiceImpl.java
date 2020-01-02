package com.example.kafka.demo.services.impl;

import com.example.kafka.demo.models.Message;
import com.example.kafka.demo.services.PublisherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.lang.invoke.MethodHandles;

@Service
public class PublisherServiceImpl implements PublisherService {
    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final Environment env;
    private KafkaTemplate<String, Message> kafkaTemplate;

    public PublisherServiceImpl(Environment env, KafkaTemplate<String, Message> kafkaTemplate) {
        this.env = env;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendMessage(Message data) {
        ListenableFuture<SendResult<String, Message>> future = kafkaTemplate.send(
                env.getRequiredProperty("spring.kafka.admin.topic-name"), data);

        future.addCallback(new ListenableFutureCallback<SendResult<String, Message>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.error("Failed to send message=[{}], due to {}", data, ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Message> result) {
                log.info("Sent message=['{}'], with offset=[{}]",
                        result.getProducerRecord().value().getMessage(),
                        result.getRecordMetadata().offset());
            }
        });
    }
}
