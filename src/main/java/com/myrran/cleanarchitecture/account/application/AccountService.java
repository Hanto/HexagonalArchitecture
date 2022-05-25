package com.myrran.cleanarchitecture.account.application;// Created by jhant on 17/05/2022.

import com.myrran.cleanarchitecture.account.application.ports.AccountDAO;
import com.myrran.cleanarchitecture.account.application.ports.AccountServiceI;
import com.myrran.cleanarchitecture.account.domain.Account;
import com.myrran.cleanarchitecture.account.domain.AccountId;
import com.myrran.cleanarchitecture.account.domain.Money;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@RequiredArgsConstructor
public class AccountService implements AccountServiceI
{
    private final AccountDAO accountDAO;

    @Override @Transactional
    public boolean giveMoney(long sourceAccountId, long targetAccountId, BigDecimal quantity)
    {
        Account sourceAccount   = accountDAO.loadAccount(new AccountId(sourceAccountId));
        Account targetAccount   = accountDAO.loadAccount(new AccountId(targetAccountId));
        Money money             = new Money(quantity);

        if (!sourceAccount.giveTo(money, targetAccount.getAccountId()))
            return false;

        if (!targetAccount.receceiveFrom(money, sourceAccount.getAccountId()))
            return false;

        accountDAO.saveAccount(sourceAccount);
        accountDAO.saveAccount(targetAccount);

        return true;
    }

    @Override
    public Account getAccount(long accountId)
    {
        return accountDAO.loadAccount(new AccountId(accountId));
    }
}