package com.example.kafka.demo.services;

import com.example.kafka.demo.models.Message;

public interface PublisherService {
    void sendMessage(Message data);
}
