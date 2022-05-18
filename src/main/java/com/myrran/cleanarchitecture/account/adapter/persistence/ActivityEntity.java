package com.myrran.cleanarchitecture.account.adapter.persistence;// Created by jhant on 17/05/2022.

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity @Table(name = "activity")
@NoArgsConstructor @AllArgsConstructor @Builder
@Getter @Setter
class ActivityEntity
{
    @Id @GeneratedValue
    private Long activityId;

    private Long sourceAccountId;

    private Long targetAccountId;

    private LocalDateTime timestamp;

    private BigDecimal money;
}