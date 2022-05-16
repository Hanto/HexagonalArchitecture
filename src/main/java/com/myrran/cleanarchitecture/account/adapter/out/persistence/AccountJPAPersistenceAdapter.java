package com.myrran.cleanarchitecture.account.adapter.out.persistence;// Created by jhant on 16/05/2022.

import com.myrran.cleanarchitecture.account.application.port.out.AccountDAOPort;
import com.myrran.cleanarchitecture.account.domain.Account;
import com.myrran.cleanarchitecture.account.domain.AccountId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor @Component
class AccountJPAPersistenceAdapter implements AccountDAOPort
{
    private final AccountJPARepository accountRepository;

    // BUSINESS:
    //--------------------------------------------------------------------------------------------------------


    @Override
    public Account loadAccount(AccountId accountId)
    {
        AccountJPAEntity accountEntity = accountRepository.findById(accountId.getValue())
            .orElseThrow(EntityNotFoundException::new);

        return null;
    }
}