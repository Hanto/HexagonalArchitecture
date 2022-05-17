package com.myrran.cleanarchitecture.account.adapter.api;// Created by jhant on 16/05/2022.

import com.myrran.cleanarchitecture.account.adapter.messagebroker.KafKaProducerAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController @RequestMapping("/api/kafka")
@RequiredArgsConstructor
class WebAdapter
{
    @Autowired private final KafKaProducerAdapter kafKaProducerAdapter;

    @GetMapping("send")
    public void sendMessage(@RequestParam String message)
    {
        kafKaProducerAdapter.processMoneyOrder(222222L, 1111L, BigDecimal.valueOf(999));
    }
}
