package com.myrran.cleanarchitecture.account.adapter.api;// Created by jhant on 25/05/2022.

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class AccountDTO
{
    private long accountId;
    private BigDecimal money;
    private List<ActivityDTO>lastActivities;
}
