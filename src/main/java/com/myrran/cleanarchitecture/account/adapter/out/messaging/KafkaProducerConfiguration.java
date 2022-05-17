package com.myrran.cleanarchitecture.account.adapter.out.messaging;// Created by jhant on 17/05/2022.

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaProducerConfiguration
{
    @Bean
    public NewTopic getMoneySendingTopic()
    {
        return TopicBuilder
            .name("moneySendingTopic")
            .partitions(6)
            .build();
    }
}
