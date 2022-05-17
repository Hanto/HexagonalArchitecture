package com.myrran.cleanarchitecture.account.adapter.messagebroker;// Created by jhant on 17/05/2022.

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
    private final KafkaTemplate<String, SendMoneyOrderDTO>kafkaTemplate;
    private final Hasher hasher;

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    public void sendMessage(String topicName, SendMoneyOrderDTO dto)
    {
        int hash = hasher.orderIndependentHash(
            String.valueOf(dto.getSourceAccoundId()), String.valueOf(dto.getTargetAccountId()));

        kafkaTemplate.send(topicName, Integer.toString(hash), dto);
    }
}