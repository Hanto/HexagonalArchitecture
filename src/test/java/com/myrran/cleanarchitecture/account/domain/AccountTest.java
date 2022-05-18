package com.myrran.cleanarchitecture.account.domain;// Created by jhant on 18/05/2022.

import lombok.Builder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AccountTest
{
    @Nested @DisplayName("WHEN: giving money")
    class Give
    {
        @Nested @DisplayName("WHEN: to a different account")
        class Different
        {
            @Nested @DisplayName("WHEN: enough funds")
            class EnoughFunds
            {
                @Test @DisplayName("THEN: success")
                void enoughFundsCanGiveMoneyToAnotherAccount()
                {
                    Account account = AccountBuilder.builder()
                        .accountId(new AccountId(1L))
                        .balance(Money.of(100))
                        .lastActivities(new LastActivities())
                        .build();

                    boolean success = account.giveTo(Money.of(10), new AccountId(2L));

                    assertThat(success).isTrue();
                    assertThat(account.getLastActivities().getActivities()).hasSize(1);
                    assertThat(account.getBalance()).isEqualTo(Money.of(90));
                }
            }

            @Nested @DisplayName("WHEN: not enough funds")
            class NotEnoghtFunds
            {
                @Test @DisplayName("THEN: failure")
                void notEnoughFundsCannotGiveMoneyToAnotherAccount()
                {
                    Account account = AccountBuilder.builder()
                        .accountId(new AccountId(1L))
                        .balance(Money.of(9))
                        .lastActivities(new LastActivities())
                        .build();

                    boolean success = account.giveTo(Money.of(10), new AccountId(2L));

                    assertThat(success).isFalse();
                    assertThat(account.getLastActivities().getActivities()).hasSize(0);
                    assertThat(account.getBalance()).isEqualTo(Money.of(9));
                }
            }
        }

        @Nested @DisplayName("WHEN: to the same account")
        class Same
        {
            @Test @DisplayName("THEN: failure")
            void cannotGiveMoneyToSameAccount()
            {
                Account account = AccountBuilder.builder()
                    .accountId(new AccountId(1L))
                    .balance(Money.of(100))
                    .lastActivities(new LastActivities())
                    .build();

                boolean success = account.giveTo(Money.of(10), new AccountId(1L));

                assertThat(success).isFalse();
                assertThat(account.getLastActivities().getActivities()).hasSize(0);
                assertThat(account.getBalance()).isEqualTo(Money.of(100));
            }
        }
    }

    @Nested @DisplayName("WHEN: receiving money")
    class Receive
    {
        @Nested @DisplayName("WHEN: to a different account")
        class Different
        {
            @Test @DisplayName("THEN: success")
            void canReceiveMoneyFromAnotherAccount()
            {
                Account account = AccountBuilder.builder()
                    .accountId(new AccountId(1L))
                    .balance(Money.of(100))
                    .lastActivities(new LastActivities())
                    .build();

                boolean success = account.receceiveFrom(Money.of(10), new AccountId(2L));

                assertThat(success).isTrue();
                assertThat(account.getLastActivities().getActivities()).hasSize(1);
                assertThat(account.getBalance()).isEqualTo(Money.of(110));
            }
        }

        @Nested @DisplayName("WHEN: to the same account")
        class Same
        {
            @Test @DisplayName("THEN: failure")
            void cannotReceiveeMoneyFromSameAccount()
            {
                Account account = AccountBuilder.builder()
                    .accountId(new AccountId(1L))
                    .balance(Money.of(100))
                    .lastActivities(new LastActivities())
                    .build();

                boolean success = account.receceiveFrom(Money.of(10), new AccountId(1L));

                assertThat(success).isFalse();
                assertThat(account.getLastActivities().getActivities()).hasSize(0);
                assertThat(account.getBalance()).isEqualTo(Money.of(100));
            }
        }
    }

    // HELPER:
    //--------------------------------------------------------------------------------------------------------

    public static class AccountBuilder extends Account
    {
        @Builder
        public AccountBuilder(AccountId accountId, Money balance, LastActivities lastActivities)
        {   super(accountId, balance, lastActivities); }
    }
}