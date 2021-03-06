package com.mycompany.crud;

import com.mycompany.types.Currency;

import java.math.BigDecimal;

/**
 * CRUD interface for account balances.
 */
public interface IAccountBalancesCRUD {

    void update(String bankTransfer) throws IllegalArgumentException;

    void update(Currency currency, BigDecimal amount) throws IllegalArgumentException;

}
