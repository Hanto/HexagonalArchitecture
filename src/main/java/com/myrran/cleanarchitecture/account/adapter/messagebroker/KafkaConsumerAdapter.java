package com.myrran.cleanarchitecture.account.adapter.messagebroker;// Created by jhant on 17/05/2022.

import com.myrran.cleanarchitecture.account.application.ports.AccountServiceI;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor @Log4j2
class KafkaConsumerAdapter
{
    private final AccountServiceI accountService;

    @KafkaListener(
        groupId         = "moneySendingGroup",
        topics          = "moneySendingTopic",
        containerFactory= "moneySendingListener",
        concurrency     = "6")
    public void receiveMessage(ConsumerRecord<String, SendMoneyOrderDTO>consumerRecord)
    {
        log.info(consumerRecord);

        SendMoneyOrderDTO dto = consumerRecord.value();

        accountService.giveMoney(dto.getSourceAccoundId(), dto.getTargetAccountId(), dto.getAmount());
    }
}