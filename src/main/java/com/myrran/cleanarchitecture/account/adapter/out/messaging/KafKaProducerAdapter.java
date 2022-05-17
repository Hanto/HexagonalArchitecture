package com.myrran.cleanarchitecture.account.adapter.out.messaging;// Created by jhant on 17/05/2022.

import com.myrran.cleanarchitecture.common.Hasher;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafKaProducerAdapter
{
    @Autowired
    private final KafkaTemplate<String, String>kafkaTemplate;
    private final Hasher hasher;

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    public void sendMessage(String topicName, String message)
    {
        int hash = hasher.orderIndependentHash(message);
        kafkaTemplate.send("moneySendingTopic", Integer.toString(hash), message);
    }
}