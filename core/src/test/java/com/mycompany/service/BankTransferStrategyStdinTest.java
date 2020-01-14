package com.mycompany.service;

import com.mycompany.TestHelper;
import com.mycompany.crud.AccountBalancesCRUD;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class BankTransferStrategyStdinTest {

    @BeforeMethod
    public void setUp() {
        TestHelper.resetAccountBalancesToZero();
    }

    @Test
    public void testIdealUC() {
        ByteArrayOutputStream bytes = getErrStream();

        final Iterator<String> bankTransfersIterator = Stream.of("USD 100", "EUR -200", "QUIT").iterator();
        final Supplier<String> stringSupplier = () -> bankTransfersIterator.hasNext() ? bankTransfersIterator.next() : null;
        new BankTransferStrategyStdin(stringSupplier).update(new AccountBalancesCRUD());

        String printedSoFar = bytes.toString(StandardCharsets.UTF_8);
        Assert.assertTrue(printedSoFar.isEmpty());
    }

    @Test
    public void testBadInput() {
        ByteArrayOutputStream bytes = getErrStream();

        final Iterator<String> bankTransfersIterator = Stream.of("USD 100", "EUR -200", "BAD input 666", "QUIT").iterator();
        final Supplier<String> stringSupplier = () -> bankTransfersIterator.hasNext() ? bankTransfersIterator.next() : null;
        new BankTransferStrategyStdin(stringSupplier).update(new AccountBalancesCRUD());

        String printedSoFar = bytes.toString(StandardCharsets.UTF_8);
        Assert.assertFalse(printedSoFar.isEmpty());
    }

    public ByteArrayOutputStream getErrStream() {
        // Set up alternate System.err PrintStream that prints to a buffer
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        PrintStream p = new PrintStream(bytes, true, StandardCharsets.UTF_8);
        System.setErr(p);
        return bytes;
    }
}