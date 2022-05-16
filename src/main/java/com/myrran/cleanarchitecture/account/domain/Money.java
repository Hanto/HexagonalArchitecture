package com.myrran.cleanarchitecture.account.domain;// Created by jhant on 16/05/2022.

import lombok.NonNull;
import lombok.Value;

import java.math.BigInteger;

@Value
public class Money
{
    @NonNull
    BigInteger amount;

    // CONSTRUCTORS:
    //--------------------------------------------------------------------------------------------------------

    public static Money of(long value)
    {   return new Money(BigInteger.valueOf(value)); }

    // BUSINESS:
    //--------------------------------------------------------------------------------------------------------

    public Money plus(Money money)
    {   return new Money( this.getAmount().add(money.getAmount()) ); }

    public Money minus(Money money)
    {   return new Money( this.getAmount().subtract(money.getAmount()) ); }
}
