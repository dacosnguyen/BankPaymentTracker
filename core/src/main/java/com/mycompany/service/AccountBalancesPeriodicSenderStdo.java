package com.mycompany.service;

import com.mycompany.persistence.Account;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Sends a timestamp and actual account balances periodically to a standard output.
 */
public class AccountBalancesPeriodicSenderStdo extends AccountBalancesPeriodicSender {

    ScheduledFuture<?> scheduledFuture;

    public AccountBalancesPeriodicSenderStdo(int period, TimeUnit timeUnit) {
        super(period, timeUnit);
    }

    @Override
    public void start(Account account) {
        scheduledFuture = Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(
                () -> {
                    System.out.println("\nAccount balances info at " + LocalDateTime.now());
                    account.getCurrencyAccountBalanceMap().values().stream()
                            .filter(accountBalance -> BigDecimal.ZERO.compareTo(accountBalance.getAmount()) != 0) // get non-zero balances
                            .forEach(System.out::println);
                }, 0, period, timeUnit);
    }

    @Override
    public void stop() {
        scheduledFuture.cancel(false);
    }

}
