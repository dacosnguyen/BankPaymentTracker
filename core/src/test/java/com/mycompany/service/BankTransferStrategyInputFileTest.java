package com.mycompany.service;

import com.mycompany.TestHelper;
import com.mycompany.crud.AccountBalancesCRUD;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class BankTransferStrategyInputFileTest {

    private final String correctBankTransfersFile = "src/test/resources/transfers_example.txt";
    private final String badBankTransfersFile = "src/test/resources/bad_transfers_example.txt";

    @BeforeMethod
    public void setUp() {
        TestHelper.resetAccountBalancesToZero();
    }

    @Test
    public void testCorrectInputFile() {
        PrintStream stdErr = System.err;
        new BankTransferStrategyInputFile(() -> correctBankTransfersFile).update(new AccountBalancesCRUD());
        Assert.assertFalse(stdErr.checkError());
    }

    @Test
    public void testBadInputFile() {
        // Set up alternate System.err PrintStream that prints to a buffer
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        PrintStream p = new PrintStream(bytes, true, StandardCharsets.UTF_8);
        System.setErr(p);
        new BankTransferStrategyInputFile(() -> badBankTransfersFile).update(new AccountBalancesCRUD());

        String printedSoFar = bytes.toString(StandardCharsets.UTF_8);
        Assert.assertTrue(printedSoFar.contains("Error while processing the bank transfer"));
    }

}