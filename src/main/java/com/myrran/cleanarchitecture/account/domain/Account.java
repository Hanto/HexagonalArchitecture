package com.myrran.cleanarchitecture.account.domain;// Created by jhant on 16/05/2022.

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Account
{
    @Getter
    private final AccountId id;
}