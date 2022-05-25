package com.myrran.cleanarchitecture.account.adapter.api;// Created by jhant on 16/05/2022.

import com.myrran.cleanarchitecture.account.application.ports.AccountServiceI;
import com.myrran.cleanarchitecture.account.application.ports.ParallelProcessing;
import com.myrran.cleanarchitecture.account.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@RestController @RequestMapping("/api")
@RequiredArgsConstructor
class WebAdapter
{
    @Autowired private final ParallelProcessing parallelProcessing;
    @Autowired private final AccountServiceI accountService;
    @Autowired private final RestTemplate restTemplate;
    @Autowired private final ActivityDTOAssembler activityAssembler;
    @Autowired private final AccountDTOAssembler accountAssembler;

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    @PostMapping("send")
    public void sendMoney(
        @NotNull @RequestParam Long sourceAccountId,
        @NotNull @RequestParam Long targetAccountId,
        @NotNull @RequestParam BigDecimal amount)
    {
        parallelProcessing.processMoneyOrder(sourceAccountId, targetAccountId, amount);
    }

    @GetMapping("account/{accountId}")
    public EntityModel<AccountDTO> getAccount(@PathVariable(value="accountId") long accountId)
    {
        Account account = accountService.getAccount(accountId);
        return accountAssembler.toModel(account);
    }

    @GetMapping("activities/{accountId}")
    public CollectionModel<EntityModel<ActivityDTO>> getActivities(@PathVariable(value="accountId") long accountId)
    {
        Account account = accountService.getAccount(accountId);
        return activityAssembler.toCollectionModel(account);
    }

    @GetMapping("testEureka")
    public EntityModel<AccountDTO> test()
    {
        return restTemplate.exchange("http://account-service/api/account/{account}",
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<EntityModel<AccountDTO>>() {},
            10L)
            .getBody();
    }
}