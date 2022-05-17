package com.myrran.cleanarchitecture.account.adapter.persistence;// Created by jhant on 17/05/2022.

import org.springframework.data.jpa.repository.JpaRepository;

interface ActivityRepository extends JpaRepository<ActivityEntity, Long>
{
}
