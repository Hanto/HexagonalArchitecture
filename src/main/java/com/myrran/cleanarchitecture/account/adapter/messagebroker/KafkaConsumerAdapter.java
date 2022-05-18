package com.myrran.cleanarchitecture.account.adapter.messagebroker;// Created by jhant on 17/05/2022.

import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component @Log4j2
class KafkaConsumerAdapter
{
    @KafkaListener(
        groupId         = "moneySendingGroup",
        topics          = "moneySendingTopic",
        containerFactory= "moneySendingListener",
        concurrency     = "6")
    public void receiveMessage(ConsumerRecord<String, SendMoneyOrderDTO>consumerRecord)
    {
        log.info(consumerRecord);

        SendMoneyOrderDTO dto = consumerRecord.value();
    }
}