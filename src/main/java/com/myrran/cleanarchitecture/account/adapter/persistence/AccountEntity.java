package com.myrran.cleanarchitecture.account.adapter.persistence;// Created by jhant on 17/05/2022.

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity @Table(name = "account")
@NoArgsConstructor @AllArgsConstructor @Builder
@Getter @Setter
class AccountEntity
{
    @Id @GeneratedValue
    private Long id;

    private BigDecimal balance;
}