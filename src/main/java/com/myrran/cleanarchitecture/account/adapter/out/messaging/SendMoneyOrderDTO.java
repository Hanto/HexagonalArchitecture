package com.myrran.cleanarchitecture.account.adapter.out.messaging;// Created by jhant on 17/05/2022.

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class SendMoneyOrderDTO implements Serializable
{
    private long sourceAccoundId;
    private long targetAccountId;
    private BigDecimal amount;
}