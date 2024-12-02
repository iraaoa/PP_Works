package com.my_complex_lab.Commands;

import com.my_complex_lab.Deposits.Deposit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FindDepositTest {

    private List<Deposit> deposits;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        deposits = new ArrayList<>();

        Deposit mockDeposit1 = mock(Deposit.class);
        when(mockDeposit1.getBankName()).thenReturn("Bank A");
        when(mockDeposit1.getDepositType()).thenReturn("О");
        deposits.add(mockDeposit1);

        Deposit mockDeposit2 = mock(Deposit.class);
        when(mockDeposit2.getBankName()).thenReturn("Bank B");
        when(mockDeposit2.getDepositType()).thenReturn("У");
        deposits.add(mockDeposit2);

        // Підготовка потоку виводу
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }





    @Test
    void testDepositNotFoundByBankName() {
        String userInput = "1\nNonexistent Bank\n";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        FindDeposit findDeposit = new FindDeposit(deposits);
        findDeposit.execute();

        String output = outputStream.toString();
        assertTrue(output.contains("Депозитів не знайдено"));
    }

    @Test
    void testDepositNotFoundByDepositType() {
        String userInput = "2\nН\n";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        FindDeposit findDeposit = new FindDeposit(deposits);
        findDeposit.execute();

        String output = outputStream.toString();
        assertTrue(output.contains("Депозитів не знайдено"));
    }

    @Test
    void testInvalidChoice() {
        String userInput = "3\n";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        FindDeposit findDeposit = new FindDeposit(deposits);
        findDeposit.execute();

        String output = outputStream.toString();
        assertTrue(output.contains("Некоректний вибір"));
    }
}
