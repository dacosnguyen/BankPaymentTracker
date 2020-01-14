package com.mycompany.engine;

import com.mycompany.crud.AccountBalancesCRUD;
import com.mycompany.service.AccountBalancesPeriodicSenderStdo;
import com.mycompany.service.BankTransferStrategyInputFile;
import com.mycompany.service.BankTransferStrategyStdin;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class CoreEnginePikachuTest {

    @Test
    public void testStart() {
        new CoreEnginePikachu<>(
                new AccountBalancesPeriodicSenderStdo(2, TimeUnit.MILLISECONDS),
                new AccountBalancesCRUD(),
                new BankTransferStrategyInputFile(() -> ""),
                new BankTransferStrategyStdin(() -> "QUIT")
        ).start();
    }
}