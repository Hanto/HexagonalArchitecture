package com.myrran.cleanarchitecture.account.adapter.persistence;// Created by jhant on 17/05/2022.

import com.myrran.cleanarchitecture.account.domain.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountMapper
{
    Account fromEntity(AccountEntity entity, List<Activity> activities)
    {
        AccountId accountId             = new AccountId(entity.getAccountId());
        Money balance                   = new Money(entity.getBalance());
        LastActivities lastActivities   = new LastActivities(activities);

        return Account.of(accountId, balance, lastActivities);
    }

    AccountEntity fromModel(Account model)
    {
        return AccountEntity.builder()
            .accountId(model.getAccountId().getValue())
            .balance(model.getBalance().getAmount())
            .build();
    }
}