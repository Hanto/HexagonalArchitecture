package com.myrran.cleanarchitecture.account.adapter.persistence;// Created by jhant on 17/05/2022.

import com.myrran.cleanarchitecture.account.domain.AccountId;
import com.myrran.cleanarchitecture.account.domain.Activity;
import com.myrran.cleanarchitecture.account.domain.ActivityId;
import com.myrran.cleanarchitecture.account.domain.Money;
import org.springframework.stereotype.Component;

@Component
public class ActivityMapper
{
    Activity fromEntity(ActivityEntity entity)
    {
        ActivityId activityId       = new ActivityId(entity.getActivityId());
        AccountId sourceAccountId   = new AccountId(entity.getSourceAccountId());
        AccountId targetAccountId   = new AccountId(entity.getTargetAccountId());
        Money money                 = new Money(entity.getMoney());

        return new Activity(activityId, sourceAccountId, targetAccountId, entity.getTimestamp(), money);
    }

    ActivityEntity fromModel(Activity model)
    {
        return ActivityEntity.builder()
            .activityId(model.getId().getValue())
            .sourceAccountId(model.getSourceAccountId().getValue())
            .targetAccountId(model.getTargetAccountId().getValue())
            .timestamp(model.getTimestamp())
            .money(model.getMoney().getAmount())
            .build();
    }
}