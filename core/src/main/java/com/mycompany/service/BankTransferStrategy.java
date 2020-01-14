package com.mycompany.service;

import com.mycompany.crud.IAccountBalancesCRUD;

/**
 * An interface for defining strategies of loading input bank transfers (from file, stdin, ...)
 */
public interface BankTransferStrategy {

    void update(IAccountBalancesCRUD crud);

}
