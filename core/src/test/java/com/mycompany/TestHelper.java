package com.mycompany;

import com.mycompany.persistence.Account;
import com.mycompany.types.AccountBalance;
import com.mycompany.types.Currency;

import java.math.BigDecimal;
import java.util.Map;
import java.util.stream.Stream;

public class TestHelper {

    /**
     * Used primarily for tests.
     */
    public static void resetAccountBalancesToZero() {
        Map<Currency, AccountBalance> balances = Account.INSTANCE.getCurrencyAccountBalanceMap();
        balances.clear();
        initBalancesToZero(balances);
    }

    private static void initBalancesToZero(Map<Currency, AccountBalance> balances) {
        Stream.of(Currency.values())
                .forEach(currency -> balances.put(
                        currency,
                        new AccountBalance(
                                currency,
                                new BigDecimal(0))) // Accounts start with 0 balance
                );
    }

}
