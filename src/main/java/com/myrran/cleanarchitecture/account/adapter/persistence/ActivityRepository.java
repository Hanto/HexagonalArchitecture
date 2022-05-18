package com.myrran.cleanarchitecture.account.adapter.persistence;// Created by jhant on 17/05/2022.

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface ActivityRepository extends JpaRepository<ActivityEntity, Long>
{
    List<ActivityEntity> findByOwnerAccountId(long sourceAccountId, Pageable page);
}
