package com.mycompany.types;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public final class AccountBalance implements Serializable {

    private Currency currency;
    private BigDecimal amount;

    public AccountBalance(Currency currency, BigDecimal amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return currency + " " + amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountBalance that = (AccountBalance) o;
        return currency == that.currency &&
                amount.equals(that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, amount);
    }
}
