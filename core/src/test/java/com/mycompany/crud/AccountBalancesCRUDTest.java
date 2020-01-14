package com.mycompany.crud;

import com.mycompany.persistence.Account;
import com.mycompany.types.Currency;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AccountBalancesCRUDTest {

    private AccountBalancesCRUD crud;

    @BeforeClass
    public void setUp() {
        crud = new AccountBalancesCRUD();
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testWrongBankTransferFormat() {
        crud.update("a a 1 0 asdf");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testWrongCurrency() {
        AccountBalancesCRUD crud = new AccountBalancesCRUD();
        crud.update("WTH 100");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testWrongAmount() {
        AccountBalancesCRUD crud = new AccountBalancesCRUD();
        crud.update("USD asdf");
    }

    @Test
    public void testIdealUC() throws InterruptedException {
        crud.update("USD 100");
        crud.update("EUR -250");
        crud.update("USD -150");
        crud.update("EUR 350");
        parallelUpdate();

        Assert.assertEquals(Account.INSTANCE.getCurrencyAccountBalanceMap().get(Currency.USD).getAmount(), new BigDecimal(-50));
        Assert.assertEquals(Account.INSTANCE.getCurrencyAccountBalanceMap().get(Currency.EUR).getAmount(), new BigDecimal(100));
        Assert.assertEquals(Account.INSTANCE.getCurrencyAccountBalanceMap().get(Currency.CZK).getAmount(), new BigDecimal(100000));
    }

    /**
     * Updates one account balance in parallel with 500 threads.
     */
    private void parallelUpdate() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(500);
        for (int i = 0; i < 1000; i++) {
            executorService.execute(() -> {
                try {
                    Thread.sleep(50); // simulates a database data updating delay
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                crud.update("CZK 100");
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);
    }
}