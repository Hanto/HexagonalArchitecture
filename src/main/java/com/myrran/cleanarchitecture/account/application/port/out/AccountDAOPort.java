package com.myrran.cleanarchitecture.account.application.port.out;// Created by jhant on 16/05/2022.

import com.myrran.cleanarchitecture.account.domain.Account;
import com.myrran.cleanarchitecture.account.domain.AccountId;

public interface AccountDAOPort
{
    Account loadAccount(AccountId accountId);
}
