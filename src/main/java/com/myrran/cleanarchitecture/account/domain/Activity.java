package com.myrran.cleanarchitecture.account.domain;// Created by jhant on 16/05/2022.

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor @RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true) @ToString
public class Activity
{
    @Getter @EqualsAndHashCode.Include
    private ActivityId id;
    @Getter @NonNull
    private final AccountId ownedAccountId;
    @Getter @NonNull
    private final AccountId sourceAccountId;
    @Getter @NonNull
    private final AccountId targetAccountId;
    @Getter @NonNull
    private final LocalDateTime timestamp;
    @Getter @NonNull
    private final Money money;

    // CONSTRUCTORS:
    //--------------------------------------------------------------------------------------------------------

    public static Activity of(AccountId ownerdAccountId, AccountId sourceAccountId, AccountId targetAccountId,
        LocalDateTime localDateTime, Money money)
    {   return new Activity(null, ownerdAccountId, sourceAccountId, targetAccountId, localDateTime, money); }
}