package com.mycompany.crud;

import com.mycompany.types.AccountBalance;
import com.mycompany.persistence.Account;
import com.mycompany.types.Currency;

import java.math.BigDecimal;
import java.util.Map;
import java.util.regex.Pattern;

public class AccountBalancesCRUD implements IAccountBalancesCRUD {

    private static final Pattern bankTransferFormat;

    static {
        // for details see src/main/resources/bank_transfers_README.md
        bankTransferFormat = Pattern.compile("^[A-Z]{3} [+-]?[0-9]{1,3}(?:,?[0-9]{3})*(?:\\.[0-9]{2})?$");
    }

    public AccountBalancesCRUD() {
    }

    @Override
    public void update(String bankTransfer) throws IllegalArgumentException {
        checkBankTransferFormat(bankTransfer);
        final Currency currency = parseCurrency(bankTransfer);
        final BigDecimal amount = parseAmount(bankTransfer);

        update(currency, amount);
    }

    @Override
    public void update(Currency currency, BigDecimal amount) throws IllegalArgumentException {
        updateAccountBalanceSynchronized(currency, amount);
    }

    private synchronized void updateAccountBalanceSynchronized(Currency currency, BigDecimal amount) {
        Map<Currency, AccountBalance> balanceMap = Account.INSTANCE.getCurrencyAccountBalanceMap();
        final BigDecimal newAmount = balanceMap.get(currency).getAmount().add(amount);
        final AccountBalance newAccountBalance = new AccountBalance(currency, newAmount);
        balanceMap.replace(currency, newAccountBalance);
    }

    private BigDecimal parseAmount(String bankTransfer) {
        String[] tokens = bankTransfer.split(" ");
        String rawAmount = tokens[1];
        try {
            return new BigDecimal(rawAmount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(String.format("The amount %s is invalid", rawAmount));
        }
    }

    private Currency parseCurrency(String bankTransfer) {
        String[] tokens = bankTransfer.split(" ");
        String rawCurrency = tokens[0];
        try {
            return Currency.valueOf(rawCurrency);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format("The currency %s is invalid or not supported", rawCurrency));
        }
    }

    private void checkBankTransferFormat(String bankTransfer) {
        if (!bankTransferFormat.matcher(bankTransfer).matches()) {
            throw new IllegalArgumentException("Invalid bank transfer format!");
        }
    }
}