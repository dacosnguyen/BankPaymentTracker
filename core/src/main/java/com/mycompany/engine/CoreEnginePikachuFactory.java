package com.mycompany.engine;

import com.mycompany.crud.AccountBalancesCRUD;
import com.mycompany.service.AccountBalancesPeriodicSenderStdo;
import com.mycompany.service.BankTransferStrategyInputFile;
import com.mycompany.service.BankTransferStrategyStdin;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class CoreEnginePikachuFactory implements AbstractCoreEngineFactory {

    public static final int PERIOD = 1;
    public static final TimeUnit TIME_UNIT = TimeUnit.MINUTES;

    public AbstractCoreEngine create() {
        return new CoreEnginePikachu<>(
                new AccountBalancesPeriodicSenderStdo(PERIOD, TIME_UNIT),
                new AccountBalancesCRUD(),
                new BankTransferStrategyInputFile(() -> new Scanner(System.in).nextLine()),
                new BankTransferStrategyStdin()
        );
    }
}
