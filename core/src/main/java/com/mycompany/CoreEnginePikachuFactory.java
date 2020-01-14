package com.mycompany;

import com.mycompany.service.AccountBalancesPeriodicSenderStdo;

public class CoreEnginePikachuFactory implements AbstractCoreEngineFactory {
    public AbstractCoreEngine create() {
        return new CoreEnginePikachu(new AccountBalancesPeriodicSenderStdo());
    }
}
