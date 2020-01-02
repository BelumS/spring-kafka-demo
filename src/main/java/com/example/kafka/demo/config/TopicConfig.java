package com.example.kafka.demo.config;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class TopicConfig {
    private final Environment env;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> props = new HashMap<>();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, env.getRequiredProperty("spring.kafka.bootstrap-servers"));
        return new KafkaAdmin(props);
    }

    @Bean
    public NewTopic newTopic() {
        return new NewTopic(env.getRequiredProperty("spring.kafka.admin.topic-name"),
                1,
                (short) 1);
    }

}
