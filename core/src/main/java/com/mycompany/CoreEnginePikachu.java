package com.mycompany;

import com.mycompany.crud.IAccountBalancesCRUD;
import com.mycompany.persistence.Account;
import com.mycompany.service.AccountBalancesPeriodicSender;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

/**
 * PIKACHU core engine processes bank transfers from a text file or a standard input.
 * The engine have a service which sends a timestamp and actual account balances periodically to an output.
 */
public class CoreEnginePikachu<T> implements AbstractCoreEngine {

    private static final String QUIT_PROGRAM_STR = "QUIT";
    private IAccountBalancesCRUD accountBalancesCRUD;
    private AccountBalancesPeriodicSender<T> accountBalancesPeriodicSender;

    public CoreEnginePikachu(AccountBalancesPeriodicSender<T> accountBalancesPeriodicSender,
                             IAccountBalancesCRUD accountBalancesCRUD) {
        this.accountBalancesPeriodicSender = accountBalancesPeriodicSender;
        this.accountBalancesCRUD = accountBalancesCRUD;
    }

    public void start() {
        updateFromInputFile();
        accountBalancesPeriodicSender.start(Account.INSTANCE);
        updateFromStdin();
        accountBalancesPeriodicSender.stop();
    }

    private void updateFromInputFile() {
        AtomicBoolean repeat = new AtomicBoolean(true);
        do {
            System.out.println("[Optional] Enter a file path to a text files with transfers (to skip just press ENTER): ");
            String filePath = new Scanner(System.in).nextLine();
            if (filePath.length() == 0) {
                System.out.println("File fetching skipped.\n");
                break;
            }

            try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
                System.out.println("File loaded successfully.\n");
                stream.forEach(bankTransfer -> {
                    try {
                        accountBalancesCRUD.update(bankTransfer);
                        repeat.set(false);
                    } catch (IllegalArgumentException e) {
                        System.err.printf("Error while processing the bank transfer: %s\n", bankTransfer);
                        System.err.println(e.toString());
                    }
                });
            } catch (IOException e) {
                System.err.printf("Error while trying to fetch from the file %s\n", filePath);
                System.err.println(e.toString());
            }
        } while (repeat.get());
    }

    private void updateFromStdin() {
        System.out.println("Enter a bank transfer and confirm it by pressing ENTER or quit by typing QUIT:");
        String bankTransfer;
        Scanner scanner = new Scanner(System.in);
        do {
            bankTransfer = scanner.nextLine();
            try {
                accountBalancesCRUD.update(bankTransfer);
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }
        } while (!QUIT_PROGRAM_STR.equals(bankTransfer));
    }

}
