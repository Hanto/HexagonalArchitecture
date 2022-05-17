package com.myrran.cleanarchitecture.account.adapter.api;// Created by jhant on 16/05/2022.

import com.myrran.cleanarchitecture.account.adapter.messagebroker.KafKaProducerAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@RestController @RequestMapping("/api/kafka")
@RequiredArgsConstructor
class WebAdapter
{
    @Autowired private final KafKaProducerAdapter kafKaProducerAdapter;

    @GetMapping("send")
    public void sendMessage(
        @NotNull @RequestParam Long sourceAccountId,
        @NotNull @RequestParam Long targetAccountId,
        @NotNull @RequestParam BigDecimal amount)
    {
        kafKaProducerAdapter.processMoneyOrder(sourceAccountId, targetAccountId, amount);
    }
}
