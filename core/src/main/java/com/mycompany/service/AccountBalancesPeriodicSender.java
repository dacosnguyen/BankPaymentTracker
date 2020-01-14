package com.mycompany.service;

import com.mycompany.persistence.Account;

import java.util.concurrent.TimeUnit;

/**
 *  Sends actual account balances periodically to a specific destination (eg. standard output, MQ ...)
 */
public interface AccountBalancesPeriodicSender {

    void start(Account account, int period, TimeUnit timeUnit);

    void stop();

}
