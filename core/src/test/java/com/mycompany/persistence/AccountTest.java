package com.mycompany.persistence;

import com.mycompany.types.AccountBalance;
import com.mycompany.types.Currency;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class AccountTest {

    private Map<Currency, AccountBalance> currencyAccountBalanceMap;

    @BeforeClass
    public void setUp() {
        currencyAccountBalanceMap = Account.INSTANCE.getCurrencyAccountBalanceMap();
    }

    @Test
    public void testNumberOfAccountBalancesEqualsToSupportedCurrencies() {
        assertEquals(currencyAccountBalanceMap.size(), Currency.values().length);
    }

    @Test
    public void testBalancesEqualsToZero() {
        currencyAccountBalanceMap.values().forEach(
                accountBalance -> Assert.assertEquals(BigDecimal.ZERO.compareTo(currencyAccountBalanceMap.get(accountBalance.getCurrency()).getAmount()), 0)
        );
    }
}