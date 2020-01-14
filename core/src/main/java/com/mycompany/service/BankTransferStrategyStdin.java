package com.mycompany.service;

import com.mycompany.crud.IAccountBalancesCRUD;

import java.util.Scanner;

public class BankTransferStrategyStdin implements BankTransferStrategy {

    private static final String QUIT_PROGRAM_STR = "QUIT";

    @Override
    public void update(IAccountBalancesCRUD crud) {
        System.out.println("Enter a bank transfer and confirm it by pressing ENTER or quit by typing QUIT:");
        String bankTransfer;
        Scanner scanner = new Scanner(System.in);
        do {
            bankTransfer = scanner.nextLine();
            try {
                crud.update(bankTransfer);
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }
        } while (!QUIT_PROGRAM_STR.equals(bankTransfer));
    }
}
