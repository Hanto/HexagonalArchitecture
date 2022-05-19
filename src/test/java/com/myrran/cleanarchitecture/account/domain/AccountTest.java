package com.myrran.cleanarchitecture.account.domain;// Created by jhant on 18/05/2022.

import lombok.Builder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AccountTest
{
    // GIVING MONEY:
    //--------------------------------------------------------------------------------------------------------

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
                void thenSuccess()
                {
                    Money money         = Money.of(10);
                    AccountId target    = new AccountId(2L);
                    Account source      = AccountBuilder.builder()
                        .accountId(new AccountId(1L))
                        .balance(Money.of(100))
                        .lastActivities(new LastActivities())
                        .build();

                    boolean success = source.giveTo(money, target);

                    assertThat(success).isTrue();
                }

                @Test @DisplayName("THEN: activiy added")
                void thenActivyAdded()
                {
                    Money money         = Money.of(10);
                    AccountId target    = new AccountId(2L);
                    Account source      = AccountBuilder.builder()
                        .accountId(new AccountId(1L))
                        .balance(Money.of(100))
                        .lastActivities(new LastActivities())
                        .build();

                    boolean success = source.giveTo(money, target);
                    Activity activity = source.getLastActivities().getActivities().get(0);

                    assertThat(source.getLastActivities().getActivities()).hasSize(1);
                    assertThat(activity.getOwnedAccountId()).isEqualTo(source.getAccountId());
                    assertThat(activity.getSourceAccountId()).isEqualTo(source.getAccountId());
                    assertThat(activity.getTargetAccountId()).isEqualTo(target);
                    assertThat(activity.getMoney()).isEqualTo(money.negative());
                }

                @Test @DisplayName("THEN: balance updated")
                void balanceUpdated()
                {
                    Money money         = Money.of(10);
                    AccountId target    = new AccountId(2L);
                    Account source      = AccountBuilder.builder()
                        .accountId(new AccountId(1L))
                        .balance(Money.of(100))
                        .lastActivities(new LastActivities())
                        .build();

                    boolean success = source.giveTo(money, target);

                    assertThat(source.getBalance()).isEqualTo(Money.of(90));
                }
            }

            @Nested @DisplayName("WHEN: not enough funds")
            class NotEnoghtFunds
            {
                @Test @DisplayName("THEN: failure")
                void thenFailure()
                {
                    Money money         = Money.of(110);
                    AccountId target    = new AccountId(2L);
                    Account source      = AccountBuilder.builder()
                        .accountId(new AccountId(1L))
                        .balance(Money.of(100))
                        .lastActivities(new LastActivities())
                        .build();

                    boolean success = source.giveTo(money, target);

                    assertThat(success).isFalse();
                }

                @Test @DisplayName("THEN: activity not added")
                void thenActivityNotAdded()
                {
                    Money money         = Money.of(110);
                    AccountId target    = new AccountId(2L);
                    Account source      = AccountBuilder.builder()
                        .accountId(new AccountId(1L))
                        .balance(Money.of(100))
                        .lastActivities(new LastActivities())
                        .build();

                    boolean success = source.giveTo(money, target);

                    assertThat(source.getLastActivities().getActivities()).hasSize(0);
                }

                @Test @DisplayName("THEN: balance not updated")
                void balancedNotUpdated()
                {
                    Money money         = Money.of(110);
                    AccountId target    = new AccountId(2L);
                    Account source      = AccountBuilder.builder()
                        .accountId(new AccountId(1L))
                        .balance(Money.of(100))
                        .lastActivities(new LastActivities())
                        .build();

                    boolean success = source.giveTo(money, target);

                    assertThat(source.getBalance()).isEqualTo(Money.of(100));
                }
            }
        }

        @Nested @DisplayName("WHEN: to the same account")
        class Same
        {
            @Test @DisplayName("THEN: failure")
            void thenFailure()
            {
                Account account = AccountBuilder.builder()
                    .accountId(new AccountId(1L))
                    .balance(Money.of(100))
                    .lastActivities(new LastActivities())
                    .build();

                boolean success = account.giveTo(Money.of(10), account.getAccountId());

                assertThat(success).isFalse();
            }

            @Test @DisplayName("THEN: activity not added")
            void thenActivityNotAdded()
            {
                Account account = AccountBuilder.builder()
                    .accountId(new AccountId(1L))
                    .balance(Money.of(100))
                    .lastActivities(new LastActivities())
                    .build();

                boolean success = account.giveTo(Money.of(10), account.getAccountId());

                assertThat(account.getLastActivities().getActivities()).hasSize(0);
            }

            @Test @DisplayName("THEN: balance not updated")
            void balancedNotUpdated()
            {
                Account account = AccountBuilder.builder()
                    .accountId(new AccountId(1L))
                    .balance(Money.of(100))
                    .lastActivities(new LastActivities())
                    .build();

                boolean success = account.giveTo(Money.of(10), account.getAccountId());

                assertThat(account.getBalance()).isEqualTo(Money.of(100));
            }
        }
    }

    // RECEIVING:
    //--------------------------------------------------------------------------------------------------------

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