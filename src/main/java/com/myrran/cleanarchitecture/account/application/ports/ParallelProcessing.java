package com.myrran.cleanarchitecture.account.application.ports;// Created by jhant on 18/05/2022.

import java.math.BigDecimal;

public interface ParallelProcessing
{
    void processMoneyOrder(long sourceAccountId, long targetAccountId, BigDecimal quantity);
}