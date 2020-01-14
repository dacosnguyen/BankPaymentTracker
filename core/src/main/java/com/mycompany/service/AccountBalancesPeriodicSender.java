package com.mycompany.service;

import com.mycompany.persistence.Account;

import java.util.concurrent.TimeUnit;

/**
 * Sends actual account balances periodically to a specific destination (eg. standard output, MQ ...)
 */
public abstract class AccountBalancesPeriodicSender {

    protected int period;
    protected TimeUnit timeUnit;

    public AccountBalancesPeriodicSender(int period, TimeUnit timeUnit) {
        this.period = period;
        this.timeUnit = timeUnit;
    }

    abstract public void start(Account account);

    abstract public void stop();

}