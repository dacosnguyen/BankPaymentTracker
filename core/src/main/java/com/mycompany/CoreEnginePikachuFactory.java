package com.mycompany;

import com.mycompany.crud.AccountBalancesCRUD;
import com.mycompany.service.AccountBalancesPeriodicSenderStdo;
import com.mycompany.service.BankTransferStrategyInputFile;
import com.mycompany.service.BankTransferStrategyStdin;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class CoreEnginePikachuFactory implements AbstractCoreEngineFactory {
    public AbstractCoreEngine create() {
        return new CoreEnginePikachu<>(
                new AccountBalancesPeriodicSenderStdo(1, TimeUnit.MINUTES),
                new AccountBalancesCRUD(),
                new BankTransferStrategyInputFile(() -> new Scanner(System.in).nextLine()),
                new BankTransferStrategyStdin()
        );
    }
}
