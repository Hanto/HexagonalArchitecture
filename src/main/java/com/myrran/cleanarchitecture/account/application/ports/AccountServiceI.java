package com.myrran.cleanarchitecture.account.application.ports;// Created by jhant on 16/05/2022.

import com.myrran.cleanarchitecture.account.domain.Account;
import com.myrran.cleanarchitecture.account.domain.Activity;

import java.math.BigDecimal;
import java.util.List;

public interface AccountServiceI
{
    boolean giveMoney(long sourceAccountId, long targetAccountId, BigDecimal quantity);
    Account getAccount(long accountId);
    List<Activity> getActivitiesOf(long accountId);
}