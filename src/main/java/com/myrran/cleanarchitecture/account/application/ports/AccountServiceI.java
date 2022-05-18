package com.myrran.cleanarchitecture.account.application.ports;// Created by jhant on 16/05/2022.

import java.math.BigDecimal;

public interface AccountServiceI
{
    boolean giveMoney(long sourceAccountId, long targetAccountId, BigDecimal quantity);
}