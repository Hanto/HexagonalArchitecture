package com.myrran.cleanarchitecture.account.adapter.in.messaging;// Created by jhant on 17/05/2022.

import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

@Configuration
public class KafkaConsumerConfiguration
{
    @Bean
    public ConsumerFactory<String, String> consumerFactory(KafkaProperties kafkaProperties)
    {
        return new DefaultKafkaConsumerFactory<>(kafkaProperties.buildConsumerProperties());
    }

    @Bean(name = "moneySendingListener")
    public ConcurrentKafkaListenerContainerFactory<String, String> moneySendingKafkaListener(KafkaProperties kafkaProperties)
    {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory(kafkaProperties));
        return factory;
    }
}