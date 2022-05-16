package com.myrran.cleanarchitecture.account.adapter.out.persistence;// Created by jhant on 16/05/2022.

import org.springframework.data.jpa.repository.JpaRepository;

interface AccountJPARepository extends JpaRepository<AccountJPAEntity, Long>
{
}
