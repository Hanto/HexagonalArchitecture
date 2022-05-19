package com.myrran.cleanarchitecture.account.adapter.api;// Created by jhant on 16/05/2022.

import com.myrran.cleanarchitecture.account.application.ports.ParallelProcessing;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@RestController @RequestMapping("/api")
@RequiredArgsConstructor
class WebAdapter
{
    @Autowired private final ParallelProcessing parallelProcessing;

    @PostMapping("send")
    public void sendMessage(
        @NotNull @RequestParam Long sourceAccountId,
        @NotNull @RequestParam Long targetAccountId,
        @NotNull @RequestParam BigDecimal amount)
    {
        parallelProcessing.processMoneyOrder(sourceAccountId, targetAccountId, amount);
    }
}