package com.myrran.cleanarchitecture.account.domain;// Created by jhant on 16/05/2022.

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true) @ToString
public class Account
{
    @Getter @EqualsAndHashCode.Include
    private final AccountId accountId;
    @Getter
    private Money balance;
    @Getter
    private final LastActivities lastActivities;

    // CONSTRUCTORS:
    //--------------------------------------------------------------------------------------------------------

    public static Account of(AccountId accountId, Money baselineBalance, LastActivities lastActivities)
    {   return new Account(accountId, baselineBalance, lastActivities); }

    public static Account of(Money baselineBalance, LastActivities lastActivities)
    {   return new Account(null, baselineBalance, lastActivities); }

    public static Account of(Money baselineBalance)
    {   return new Account(null, baselineBalance, new LastActivities()); }

    // BUSINESS:
    //--------------------------------------------------------------------------------------------------------

    public boolean giveTo(Money money, AccountId targetAccountId)
    {
        if (!mayWithdraw(money) || areSameAccount(accountId, targetAccountId))
            return false;

        Activity withdrawal = new Activity(accountId, accountId, targetAccountId, LocalDateTime.now(), money.negative());
        lastActivities.addActivity(withdrawal);

        this.balance = balance.minus(money);

        return true;
    }

    public boolean receceiveFrom(Money money, AccountId sourceAccountId)
    {
        if (areSameAccount(accountId, sourceAccountId))
            return false;

        Activity deposit = new Activity(accountId, sourceAccountId, accountId, LocalDateTime.now(), money);
        lastActivities.addActivity(deposit);

        balance = balance.plus(money);

        return true;
    }
    
    private boolean mayWithdraw(Money money)
    {   return balance.minus(money).isPositive(); }

    private boolean areSameAccount(AccountId acc1, AccountId acc2)
    {   return acc1.equals(acc2); }
}