package com.myrran.cleanarchitecture.account.domain;// Created by jhant on 16/05/2022.

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor @EqualsAndHashCode(onlyExplicitlyIncluded = true) @ToString
public class Account
{
    @Getter @EqualsAndHashCode.Include
    private final AccountId accountId;
    
    @Getter
    private final Money baselineBalance;
    
    @Getter 
    private final LastActivities lastActivities;

    // CONSTRUCTORS:
    //--------------------------------------------------------------------------------------------------------
    
    public static Account of(Money baselineBalance, LastActivities lastActivities)
    {   return new Account(null, baselineBalance, lastActivities); }

    public static Account of(AccountId accountId, Money baselineBalance, LastActivities lastActivities)
    {   return new Account(accountId, baselineBalance, lastActivities); }

    // BUSINESS:
    //--------------------------------------------------------------------------------------------------------

    public boolean giveTo(Money money, AccountId targetAccountId)
    {
        if (!mayWithdraw(money) || areSameAccount(accountId, targetAccountId))
            return false;

        Activity withdrawal = new Activity(accountId, targetAccountId, LocalDateTime.now(), money);
        lastActivities.addActivity(withdrawal);

        return true;
    }

    public boolean receceiveFrom(Money money, AccountId sourceAccountId)
    {
        if (areSameAccount(accountId, sourceAccountId))
            return false;

        Activity deposit = new Activity(sourceAccountId, accountId, LocalDateTime.now(), money);
        lastActivities.addActivity(deposit);

        return true;
    }
    
    private boolean mayWithdraw(Money money)
    {   return calculateBalance().minus(money).isPositive(); }

    private boolean areSameAccount(AccountId acc1, AccountId acc2)
    {   return acc1.equals(acc2); }
    

    // CALCULATIONS:
    //--------------------------------------------------------------------------------------------------------

    public Money calculateBalance()
    {
        Money differenceFromBaseline = lastActivities.calculateBalance(accountId);
        return baselineBalance.plus(differenceFromBaseline);
    }

}