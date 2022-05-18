package com.myrran.cleanarchitecture.account.adapter.messagebroker;// Created by jhant on 17/05/2022.

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Builder @NoArgsConstructor @AllArgsConstructor @Getter @ToString
class SendMoneyOrderDTO implements Serializable
{
    private long sourceAccoundId;
    private long targetAccountId;
    private BigDecimal amount;
}