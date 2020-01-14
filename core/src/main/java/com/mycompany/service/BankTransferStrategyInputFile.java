package com.mycompany.service;

import com.mycompany.crud.IAccountBalancesCRUD;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class BankTransferStrategyInputFile implements BankTransferStrategy {

    private Supplier<String> filePathSupplier;

    public BankTransferStrategyInputFile(Supplier<String> filePathSupplier) {
        this.filePathSupplier = filePathSupplier;
    }

    @Override
    public void update(IAccountBalancesCRUD crud) {
        AtomicBoolean repeat = new AtomicBoolean(true);
        do {
            System.out.println("[Optional] Enter a file path to a text files with transfers (to skip just press ENTER): ");
            String filePath = filePathSupplier.get();
            if (filePath.length() == 0) {
                System.out.println("File fetching skipped.\n");
                break;
            }

            try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
                System.out.println("File loaded successfully.\n");
                stream.forEach(bankTransfer -> {
                    try {
                        crud.update(bankTransfer);
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
}
