package com.myrran.cleanarchitecture.account.adapter.messagebroker;// Created by jhant on 17/05/2022.

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.Map;

@Configuration
class KafkaConfiguration
{
    // MONEY SENDING
    //--------------------------------------------------------------------------------------------------------

    @Bean
    public NewTopic getMoneySendingTopic()
    {
        return TopicBuilder
            .name("moneySendingTopic")
            .partitions(6)
            .build();
    }

    // MONEY SENDING: producer
    //--------------------------------------------------------------------------------------------------------

    @Bean
    public ProducerFactory<String, SendMoneyOrderDTO>getMoneySendingProducerFacgtory(KafkaProperties kafkaProperties)
    {
        Map<String, Object> props = kafkaProperties.buildConsumerProperties();
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean(name = "moneySendingTemplate")
    public KafkaTemplate<String, SendMoneyOrderDTO>getMoneySendingKafkaTemplate(KafkaProperties kafkaProperties)
    {   return new KafkaTemplate<>(getMoneySendingProducerFacgtory(kafkaProperties)); }

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