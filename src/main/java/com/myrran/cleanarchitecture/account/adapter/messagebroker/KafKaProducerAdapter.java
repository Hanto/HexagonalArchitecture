package com.myrran.cleanarchitecture.account.adapter.messagebroker;// Created by jhant on 17/05/2022.

import com.myrran.cleanarchitecture.account.application.ports.ParallelProcessing;
import com.myrran.cleanarchitecture.common.Hasher;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor @Log4j2
class KafKaProducerAdapter implements ParallelProcessing
{
    @Autowired private final KafkaTemplate<String, SendMoneyOrderDTO>kafkaTemplate;
    @Autowired private final Hasher hasher;

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    @Override
    @Retryable(
        value = {Exception.class},
        maxAttempts = 3,
        backoff = @Backoff(delay = 1000, multiplier = 2, maxDelay = 100000))
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

    @Recover
    public void recover(Exception e)
    {
        log.error("Could not retry", e);
        throw new RuntimeException(e);
    }
}