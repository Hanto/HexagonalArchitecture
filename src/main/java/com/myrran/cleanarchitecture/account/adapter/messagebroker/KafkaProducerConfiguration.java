package com.myrran.cleanarchitecture.account.adapter.messagebroker;// Created by jhant on 17/05/2022.

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.Map;

@Configuration
class KafkaProducerConfiguration
{
    // MONEY SENDING: producer
    //--------------------------------------------------------------------------------------------------------

    @Bean
    public NewTopic getMoneySendingTopic()
    {
        return TopicBuilder
            .name("moneySendingTopic")
            .partitions(6)
            .build();
    }

    @Bean
    public ProducerFactory<String, SendMoneyOrderDTO>getMoneySendingProducerFacgtory(KafkaProperties kafkaProperties)
    {
        Map<String, Object> props = kafkaProperties.buildConsumerProperties();
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean
    public KafkaTemplate<String, SendMoneyOrderDTO>getMoneySendingKafkaTemplate(KafkaProperties kafkaProperties)
    {   return new KafkaTemplate<>(getMoneySendingProducerFacgtory(kafkaProperties)); }
}