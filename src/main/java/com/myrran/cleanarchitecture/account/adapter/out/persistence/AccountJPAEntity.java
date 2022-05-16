package com.myrran.cleanarchitecture.account.adapter.out.persistence;// Created by jhant on 17/05/2022.

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity @Table(name = "account")
@NoArgsConstructor @AllArgsConstructor @Data
class AccountJPAEntity
{
    @Id @GeneratedValue
    private Long Id;
}
