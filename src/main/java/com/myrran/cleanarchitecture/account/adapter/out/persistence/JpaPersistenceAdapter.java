package com.myrran.cleanarchitecture.account.adapter.out.persistence;// Created by jhant on 16/05/2022.

import com.myrran.cleanarchitecture.account.application.port.out.AccountDAOPort;
import com.myrran.cleanarchitecture.account.domain.Account;
import com.myrran.cleanarchitecture.account.domain.AccountId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor @Component
class JpaPersistenceAdapter implements AccountDAOPort
{
    private final AccountRepository accountRepository;
    private final ActivityRepository activityRepository;

    // BUSINESS:
    //--------------------------------------------------------------------------------------------------------

    @Override
    public Account loadAccount(AccountId accountId)
    {
        AccountEntity accountEntity = accountRepository.findById(accountId.getValue())
            .orElseThrow(EntityNotFoundException::new);


        return null;
    }
}