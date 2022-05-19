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

    public static Money of(float value)
    {   return new Money(BigDecimal.valueOf(value)); }

    public static Money ofZero()
    {   return new Money(BigDecimal.ZERO); }

    // BUSINESS:
    //--------------------------------------------------------------------------------------------------------

    public Money plus(Money money)
    {   return new Money( this.getAmount().add(money.getAmount()) ); }

    public Money minus(Money money)
    {   return new Money( this.getAmount().subtract(money.getAmount()) ); }

    // CALCULATIONS:
    //--------------------------------------------------------------------------------------------------------

    public boolean isPositive()
    {   return amount.compareTo(BigDecimal.ZERO) >= 0; }

    public Money negative()
    {   return new Money(amount.negate()); }
}