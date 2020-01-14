package com.mycompany.service;

import com.mycompany.crud.IAccountBalancesCRUD;

import java.util.Scanner;
import java.util.function.Supplier;

public class BankTransferStrategyStdin implements BankTransferStrategy {

    private static final String QUIT_PROGRAM_STR = "QUIT";
    private static final Scanner scanner = new Scanner(System.in);
    final Supplier<String> stringSupplier;

    public BankTransferStrategyStdin() {
        this(scanner::nextLine);
    }

    /**
     * Used primarily for tests.
     */
    protected BankTransferStrategyStdin(Supplier<String> stringSupplier) {
        this.stringSupplier = stringSupplier;
    }

    @Override
    public void update(IAccountBalancesCRUD crud) {
        System.out.println("Enter a bank transfer and confirm it by pressing ENTER or quit by typing QUIT:");
        String bankTransfer;

        while (!QUIT_PROGRAM_STR.equals(bankTransfer = stringSupplier.get())) {
            try {
                crud.update(bankTransfer);
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
