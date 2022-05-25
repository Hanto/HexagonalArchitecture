package com.myrran.cleanarchitecture.account.adapter.api;// Created by jhant on 25/05/2022.

import com.myrran.cleanarchitecture.account.domain.Account;
import com.myrran.cleanarchitecture.account.domain.Activity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountDTOMapper
{
    AccountDTO fromModel(Account account)
    {
        List<ActivityDTO> lastActivitiesDTO = account.getLastActivities().getActivities().stream()
            .map(this::fromModel).toList();

        return AccountDTO.builder()
            .accountId(account.getAccountId().getValue())
            .money(account.getBalance().getAmount())
            .lastActivities(lastActivitiesDTO)
            .build();
    }

    ActivityDTO fromModel(Activity activity)
    {
        return ActivityDTO.builder()
            .id(activity.getId().getValue())
            .ownerAccountId(activity.getOwnedAccountId().getValue())
            .sourceAccountId(activity.getSourceAccountId().getValue())
            .targetAccountId(activity.getTargetAccountId().getValue())
            .timestamp(activity.getTimestamp())
            .money(activity.getMoney().getAmount())
            .build();
    }
}