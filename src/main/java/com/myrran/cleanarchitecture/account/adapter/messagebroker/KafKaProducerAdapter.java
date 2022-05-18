package com.myrran.cleanarchitecture.account.adapter.messagebroker;// Created by jhant on 17/05/2022.

import com.myrran.cleanarchitecture.account.application.ports.ParallelProcessing;
import com.myrran.cleanarchitecture.common.Hasher;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
class KafKaProducerAdapter implements ParallelProcessing
{
    @Autowired
    private final KafkaTemplate<String, SendMoneyOrderDTO>kafkaTemplate;
    private final Hasher hasher;

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    @Override
    public void processMoneyOrder(long sourceAccountId, long targetAccountId, BigDecimal quantity)
    {
        SendMoneyOrderDTO dto = SendMoneyOrderDTO.builder()
            .sourceAccoundId(sourceAccountId)
            .targetAccountId(targetAccountId)
            .amount(quantity)
            .build();

        int hash = hasher.orderIndependentHash(String.valueOf(dto.getSourceAccoundId()));

        kafkaTemplate.send("moneySendingTopic", Integer.toString(hash), dto);
    }
}