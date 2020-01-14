package com.mycompany.persistence;

import com.mycompany.types.AccountBalance;
import com.mycompany.types.Currency;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

/**
 * A user account containing account balances.
 * This simulates a database object.
 * This singleton is implemented as enum (recommended in Effective Java by Joshua Bloch).
 */
public enum Account {

    INSTANCE;

    private final static Map<Currency, AccountBalance> currencyAccountBalanceMap;

    static {
        currencyAccountBalanceMap = initAcountBalances();
    }

    private static Map<Currency, AccountBalance> initAcountBalances() {
        Map<Currency, AccountBalance> balances;
        balances = new ConcurrentHashMap<>();
        Stream.of(Currency.values())
                .forEach(currency -> balances.put(
                        currency,
                        new AccountBalance(
                                currency,
                                new BigDecimal(0))) // Accounts start with 0 balance
                );
        return balances;
    }

    public Map<Currency, AccountBalance> getCurrencyAccountBalanceMap() {
        return currencyAccountBalanceMap;
    }
}
