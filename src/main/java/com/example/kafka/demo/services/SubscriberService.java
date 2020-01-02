package com.example.kafka.demo.services;

import com.example.kafka.demo.models.Message;

public interface SubscriberService {
    void listen(Message message);
}
