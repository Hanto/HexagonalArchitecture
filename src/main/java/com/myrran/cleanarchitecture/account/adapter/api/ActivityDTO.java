package com.myrran.cleanarchitecture.account.adapter.api;// Created by jhant on 25/05/2022.

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ActivityDTO
{
    private long id;
    private long ownerAccountId;
    private long sourceAccountId;
    private long targetAccountId;
    private LocalDateTime timestamp;
    private BigDecimal money;
}
