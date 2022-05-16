package com.myrran.cleanarchitecture.account.domain;// Created by jhant on 16/05/2022.

import lombok.NonNull;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class Money
{
    @NonNull
    BigDecimal amount;

    // CONSTRUCTORS:
    //--------------------------------------------------------------------------------------------------------

    public static Money of(long value)
    {   return new Money(BigDecimal.valueOf(value)); }

    // BUSINESS:
    //--------------------------------------------------------------------------------------------------------

    public Money plus(Money money)
    {   return new Money( this.getAmount().add(money.getAmount()) ); }

    public Money minus(Money money)
    {   return new Money( this.getAmount().subtract(money.getAmount()) ); }

    // CALCULATIONS:
    //--------------------------------------------------------------------------------------------------------

    public boolean isPositive()
    {   return amount.compareTo(BigDecimal.ZERO) >0; }
}