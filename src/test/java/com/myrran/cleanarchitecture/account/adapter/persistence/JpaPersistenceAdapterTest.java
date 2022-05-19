package com.myrran.cleanarchitecture.account.adapter.persistence;// Created by jhant on 19/05/2022.

import com.myrran.cleanarchitecture.account.domain.Account;
import com.myrran.cleanarchitecture.account.domain.AccountId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({JpaPersistenceAdapter.class, AccountMapper.class, ActivityMapper.class})
public class JpaPersistenceAdapterTest
{
    @Autowired private JpaPersistenceAdapter adapter;
    @Autowired private AccountRepository accountRepository;
    @Autowired private ActivityRepository activityRepository;

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    @Test
    @Sql("/test.sql")
    void loadTest()
    {
        Account account1 = adapter.loadAccount(new AccountId(1L));
        Account account2 = adapter.loadAccount(new AccountId(2L));

        assertThat(account1.getBalance().getAmount()).isEqualByComparingTo(BigDecimal.valueOf(1000));
        assertThat(account1.getLastActivities().getActivities().size()).isEqualTo(4);

        assertThat(account2.getBalance().getAmount()).isEqualByComparingTo(BigDecimal.valueOf(4000));
        assertThat(account1.getLastActivities().getActivities().size()).isEqualTo(4);
    }
}
