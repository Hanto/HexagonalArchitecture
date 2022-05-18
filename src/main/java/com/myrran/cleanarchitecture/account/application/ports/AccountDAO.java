package com.myrran.cleanarchitecture.account.application.ports;// Created by jhant on 16/05/2022.

import com.myrran.cleanarchitecture.account.domain.Account;
import com.myrran.cleanarchitecture.account.domain.AccountId;

public interface AccountDAO
{
    Account loadAccount(AccountId accountId);
    void saveAccount(Account account);
}