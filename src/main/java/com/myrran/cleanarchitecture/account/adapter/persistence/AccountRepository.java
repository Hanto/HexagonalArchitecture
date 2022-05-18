package com.myrran.cleanarchitecture.account.adapter.persistence;// Created by jhant on 16/05/2022.

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import java.util.Optional;

interface AccountRepository extends JpaRepository<AccountEntity, Long>
{
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    //@QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "3000")})
    Optional<AccountEntity>findById(Long accountId);
}