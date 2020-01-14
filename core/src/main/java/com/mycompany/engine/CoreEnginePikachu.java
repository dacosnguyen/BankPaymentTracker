package com.mycompany.engine;

import com.mycompany.crud.IAccountBalancesCRUD;
import com.mycompany.persistence.Account;
import com.mycompany.service.AccountBalancesPeriodicSender;
import com.mycompany.service.BankTransferStrategy;
import com.mycompany.service.BankTransferStrategyInputFile;
import com.mycompany.service.BankTransferStrategyStdin;

/**
 * PIKACHU core engine processes bank transfers from a text file and a standard input. This is the default core engine.
 * The engine have a service which sends a timestamp and actual account balances periodically to an output.
 * <p>
 * O is a type of {@link AccountBalancesPeriodicSender} Output.
 */
public class CoreEnginePikachu<O> implements AbstractCoreEngine {
    private final IAccountBalancesCRUD accountBalancesCRUD;
    private final AccountBalancesPeriodicSender<O> accountBalancesPeriodicSender;
    private final BankTransferStrategy bankTransferStrategyInputFile;
    private final BankTransferStrategy bankTransferStrategyStdin;

    public CoreEnginePikachu(AccountBalancesPeriodicSender<O> accountBalancesPeriodicSender,
                             IAccountBalancesCRUD accountBalancesCRUD,
                             BankTransferStrategyInputFile bankTransferStrategyInputFile,
                             BankTransferStrategyStdin bankTransferStrategyStdin) {
        this.accountBalancesPeriodicSender = accountBalancesPeriodicSender;
        this.accountBalancesCRUD = accountBalancesCRUD;
        this.bankTransferStrategyInputFile = bankTransferStrategyInputFile;
        this.bankTransferStrategyStdin = bankTransferStrategyStdin;
    }

    public void start() {
        bankTransferStrategyInputFile.update(accountBalancesCRUD);
        accountBalancesPeriodicSender.start(Account.INSTANCE);
        bankTransferStrategyStdin.update(accountBalancesCRUD);
        accountBalancesPeriodicSender.stop();
    }

}
