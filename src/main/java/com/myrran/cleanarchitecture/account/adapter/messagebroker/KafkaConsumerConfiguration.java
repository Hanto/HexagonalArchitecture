package com.myrran.cleanarchitecture.account.adapter.messagebroker;// Created by jhant on 17/05/2022.

import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Configuration
class KafkaConsumerConfiguration
{
    // MONEY SENDING: consumer
    //--------------------------------------------------------------------------------------------------------

    @Bean
    public ConsumerFactory<String, SendMoneyOrderDTO> consumerFactory(KafkaProperties kafkaProperties)
    {
        return new DefaultKafkaConsumerFactory<>(kafkaProperties.buildConsumerProperties(),
            new StringDeserializer(),
            new JsonDeserializer<>(SendMoneyOrderDTO.class));
    }

    @Bean(name = "moneySendingListener")
    public ConcurrentKafkaListenerContainerFactory<String, SendMoneyOrderDTO> moneySendingKafkaListener(KafkaProperties kafkaProperties)
    {
        ConcurrentKafkaListenerContainerFactory<String, SendMoneyOrderDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory(kafkaProperties));
        return factory;
    }
}