package com.myrran.cleanarchitecture.account.adapter.messagebroker;// Created by jhant on 17/05/2022.

import com.myrran.cleanarchitecture.account.application.ports.AccountServiceI;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.retrytopic.DltStrategy;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
@RequiredArgsConstructor @Log4j2
class KafkaConsumerAdapter
{
    private final AccountServiceI accountService;

    // CONSUMER:
    //--------------------------------------------------------------------------------------------------------

    @RetryableTopic
    (
        kafkaTemplate = "moneySendingTemplate",
        attempts = "3",
        backoff = @Backoff(delay = 1000, multiplier = 3.0, maxDelay = 10000),
        dltStrategy = DltStrategy.FAIL_ON_ERROR,
        exclude =
        {
            EntityNotFoundException.class
        }
    )
    @KafkaListener
    (
        groupId         = "moneySendingGroup",
        topics          = "moneySendingTopic",
        containerFactory= "moneySendingListener",
        concurrency     = "6"
    )
    public void receiveMessage(ConsumerRecord<String, SendMoneyOrderDTO>consumerRecord)
    {
        log.info(consumerRecord);

        SendMoneyOrderDTO dto = consumerRecord.value();

        accountService.giveMoney(dto.getSourceAccoundId(), dto.getTargetAccountId(), dto.getAmount());
    }

    // DEAD LETTER:
    //--------------------------------------------------------------------------------------------------------

    @DltHandler
    public void dlt(SendMoneyOrderDTO in,
        @Header(KafkaHeaders.EXCEPTION_MESSAGE) String errorMessage,
        @Header(KafkaHeaders.RECEIVED_TOPIC) String topic)
    {   log.info("DEAD LETTER TOPIC: " + in + " from " + topic + " because: " + errorMessage); }
}