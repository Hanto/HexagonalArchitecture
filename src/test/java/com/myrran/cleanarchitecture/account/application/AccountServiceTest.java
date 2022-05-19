package com.myrran.cleanarchitecture.account.application;// Created by jhant on 19/05/2022.

import com.myrran.cleanarchitecture.account.application.ports.AccountDAO;
import com.myrran.cleanarchitecture.account.domain.Account;
import com.myrran.cleanarchitecture.account.domain.AccountId;
import com.myrran.cleanarchitecture.account.domain.LastActivities;
import com.myrran.cleanarchitecture.account.domain.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountServiceTest extends BDDMockito
{
    private final AccountDAO dao = mock(AccountDAO.class);
    private final AccountService service = new AccountService(dao);

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    @Nested @DisplayName("WHEN: source and target accounts are the same")
    class sameAccount
    {
        @Test @DisplayName("THEN: no change in the accounts")
        void failure()
        {
            givenAnAccountWithId(1, 100);

            boolean success = service.giveMoney(1, 1, BigDecimal.valueOf(200));

            assertThat(success).isFalse();

            thenAccountsSaved(0);
        }
    }

    @Nested @DisplayName("WHEN: source and target accounts are different")
    class differentAccount
    {
        @Nested @DisplayName("WHEN: source account has funds to give")
        class hasFunds
        {
            @Test @DisplayName("THEN: accounts updated")
            void success()
            {
                givenAnAccountWithId(1, 100);
                givenAnAccountWithId(2, 200);

                boolean success = service.giveMoney(1, 2, BigDecimal.valueOf(100));

                assertThat(success).isTrue();

                List<Account> saved = thenAccountsSaved(2);

                Optional<Account>account1 = findAccount(1, saved);
                Optional<Account>account2 = findAccount(2, saved);

                assertThat(account1).isPresent();
                assertThat(account2).isPresent();
                assertThat(account1.get().getBalance().getAmount()).isEqualByComparingTo(BigDecimal.valueOf(0));
                assertThat(account2.get().getBalance().getAmount()).isEqualByComparingTo(BigDecimal.valueOf(300));
            }
        }

        @Nested @DisplayName("WHEN: source account doesn't have funds to give")
        class doesntHaveFunds
        {

            @Test @DisplayName("THEN: no change in the accounts")
            void failure()
            {
                givenAnAccountWithId(1, 100);
                givenAnAccountWithId(2, 200);

                boolean success = service.giveMoney(1, 2, BigDecimal.valueOf(200));

                assertThat(success).isFalse();

                thenAccountsSaved(0);
            }
        }
    }

    // HELPER:
    //--------------------------------------------------------------------------------------------------------

    void givenAnAccountWithId(long id, float money)
    {
        AccountId accountId = new AccountId(id);
        Money quantity = Money.of(money);
        Account account = Account.of(accountId, quantity, new LastActivities());

        given(dao.loadAccount(accountId))
            .willReturn(account);
    }

    List<Account> thenAccountsSaved(int numberOfAccountsSaved)
    {
        ArgumentCaptor<Account>accountCaptor = ArgumentCaptor.forClass(Account.class);
        then(dao)
            .should(times(numberOfAccountsSaved))
            .saveAccount(accountCaptor.capture());

        return accountCaptor.getAllValues();
    }

    Optional<Account> findAccount(long accoundId, List<Account>list)
    {
        return list.stream()
            .filter(account -> account.getAccountId().getValue() == accoundId)
            .findFirst();
    }
}