package com.myrran.cleanarchitecture.account.adapter.persistence;// Created by jhant on 16/05/2022.

import org.springframework.data.jpa.repository.JpaRepository;

interface AccountRepository extends JpaRepository<AccountEntity, Long>
{
}