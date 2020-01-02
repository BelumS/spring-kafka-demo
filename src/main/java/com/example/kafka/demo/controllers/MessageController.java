package com.example.kafka.demo.controllers;

import com.example.kafka.demo.models.Message;
import com.example.kafka.demo.services.PublisherService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.invoke.MethodHandles;
import java.util.Objects;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class MessageController {
    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final PublisherService service;

    @PostMapping("/send")
    public void send(@RequestBody Message message) {
        try {
            service.sendMessage(Objects.requireNonNull(message));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

    }

}
