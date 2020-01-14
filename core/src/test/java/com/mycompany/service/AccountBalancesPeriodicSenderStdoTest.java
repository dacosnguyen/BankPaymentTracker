package com.mycompany.service;

import com.mycompany.crud.AccountBalancesCRUD;
import com.mycompany.crud.IAccountBalancesCRUD;
import com.mycompany.persistence.Account;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class AccountBalancesPeriodicSenderStdoTest {

    @Test
    public void testStart() throws InterruptedException {
        IAccountBalancesCRUD crud = new AccountBalancesCRUD();
        AccountBalancesPeriodicSender<String> sender = new AccountBalancesPeriodicSenderStdo(100, TimeUnit.MILLISECONDS);
        sender.start(Account.INSTANCE);

        Assert.assertEquals(sender.getLastOutput(), "");

        Thread.sleep(50);

        crud.update("USD 100");
        crud.update("EUR -200");
        Thread.sleep(100);
        Assert.assertTrue(sender.getLastOutput().contains("USD 100"));
        Assert.assertTrue(sender.getLastOutput().contains("EUR -200"));

        crud.update("CZK 250");
        crud.update("EUR 200");
        Thread.sleep(100);
        Assert.assertTrue(sender.getLastOutput().contains("USD 100"));
        Assert.assertTrue(sender.getLastOutput().contains("CZK 250"));
        Assert.assertFalse(sender.getLastOutput().contains("EUR"));
    }

}