package com.mycompany;

import com.mycompany.crud.IAccountBalancesCRUD;
import com.mycompany.persistence.Account;
import com.mycompany.service.AccountBalancesPeriodicSender;
import com.mycompany.service.BankTransferStrategyInputFile;
import com.mycompany.service.BankTransferStrategyStdin;

import java.util.Scanner;

/**
 * PIKACHU core engine processes bank transfers from a text file or a standard input.
 * The engine have a service which sends a timestamp and actual account balances periodically to an output.
 * <p>
 * O is a type of {@link AccountBalancesPeriodicSender} Output
 */
public class CoreEnginePikachu<O> implements AbstractCoreEngine {
    private IAccountBalancesCRUD accountBalancesCRUD;
    private AccountBalancesPeriodicSender<O> accountBalancesPeriodicSender;

    public CoreEnginePikachu(AccountBalancesPeriodicSender<O> accountBalancesPeriodicSender,
                             IAccountBalancesCRUD accountBalancesCRUD) {
        this.accountBalancesPeriodicSender = accountBalancesPeriodicSender;
        this.accountBalancesCRUD = accountBalancesCRUD;
    }

    public void start() {
        new BankTransferStrategyInputFile(() -> new Scanner(System.in).nextLine()).update(accountBalancesCRUD);
        accountBalancesPeriodicSender.start(Account.INSTANCE);
        new BankTransferStrategyStdin().update(accountBalancesCRUD);
        accountBalancesPeriodicSender.stop();
    }

}
