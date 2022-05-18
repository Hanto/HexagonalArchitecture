package com.myrran.cleanarchitecture.account.adapter.persistence;// Created by jhant on 16/05/2022.

import com.myrran.cleanarchitecture.account.application.ports.AccountDAO;
import com.myrran.cleanarchitecture.account.domain.Account;
import com.myrran.cleanarchitecture.account.domain.AccountId;
import com.myrran.cleanarchitecture.account.domain.Activity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RequiredArgsConstructor @Component
class JpaPersistenceAdapter implements AccountDAO
{
    private final AccountRepository accountRepository;
    private final ActivityRepository activityRepository;
    private final AccountMapper accountMapper;
    private final ActivityMapper activityMapper;

    // BUSINESS:
    //--------------------------------------------------------------------------------------------------------

    @Override
    public Account loadAccount(AccountId accountId)
    {
        AccountEntity accountEntity = accountRepository.findById(accountId.getValue())
            .orElseThrow(EntityNotFoundException::new);

        List<ActivityEntity> activityEntities = activityRepository
            .findBySourceAccountIdOrTargetAccountId(accountId.getValue(), accountId.getValue(), Pageable.ofSize(10));

        List<Activity>activities = activityEntities.stream().map(activityMapper::fromEntity).toList();

        return accountMapper.fromEntity(accountEntity, activities);
    }
}