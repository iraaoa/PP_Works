package com.my_complex_lab.Commands;

import com.my_complex_lab.Deposits.Deposit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WithdrawEarlyTest {

    private List<Deposit> myDeposits;
    private ByteArrayOutputStream outputStream;
    private Deposit mockDeposit;
    private static final int DEPOSIT_ID = 123;

    @BeforeEach
    void setUp() {
        myDeposits = new ArrayList<>();
        mockDeposit = mock(Deposit.class);
        when(mockDeposit.getDepositID()).thenReturn(DEPOSIT_ID);
        myDeposits.add(mockDeposit);

        // Підготовка потоку виводу
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void testWithdrawEarlySuccess() {
        String userInput = DEPOSIT_ID + "\n500\n";  // Введення ID депозиту та суми для зняття
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        WithdrawEarly withdrawEarly = new WithdrawEarly(myDeposits);
        withdrawEarly.execute();

        String output = outputStream.toString();
        assertTrue(output.contains("Оновлений депозит"));
        verify(mockDeposit, times(1)).GetMoney(500);
    }

    @Test
    void testWithdrawEarlyDepositNotFound() {
        String userInput = "999\n500\n";  // Введення невірного ID депозиту
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        WithdrawEarly withdrawEarly = new WithdrawEarly(myDeposits);
        withdrawEarly.execute();

        String output = outputStream.toString();
        assertTrue(output.contains("У вас немає депозиту з таким ID"));
    }

    @Test
    void testWithdrawEarlyNegativeAmount() {
        String userInput = DEPOSIT_ID + "\n-500\n";  // Введення негативної суми для зняття
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        WithdrawEarly withdrawEarly = new WithdrawEarly(myDeposits);
        withdrawEarly.execute();

        String output = outputStream.toString();
        assertTrue(output.contains("Сума для зняття має бути позитивною"));
    }

    @Test
    void testWithdrawEarlyInvalidInput() {
        String userInput = "invalid\n";  // Некоректне введення ID
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        WithdrawEarly withdrawEarly = new WithdrawEarly(myDeposits);
        withdrawEarly.execute();

        String output = outputStream.toString();
        assertTrue(output.contains("Помилка вводу. Будь ласка, введіть коректний ID"));
    }

}
