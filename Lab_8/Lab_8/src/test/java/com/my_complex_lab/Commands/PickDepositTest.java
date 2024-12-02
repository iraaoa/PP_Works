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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PickDepositTest {

    private List<Deposit> deposits;
    private List<Deposit> myDeposits;
    private ByteArrayOutputStream outputStream;
    private static final int DEPOSIT_ID = 123;
    private Deposit mockDeposit;

    @BeforeEach
    void setUp() {
        deposits = new ArrayList<>();
        myDeposits = new ArrayList<>();

        // Створення мока депозиту
        mockDeposit = mock(Deposit.class);
        when(mockDeposit.getDepositID()).thenReturn(DEPOSIT_ID);
        when(mockDeposit.getBankName()).thenReturn("Test Bank");

        deposits.add(mockDeposit);

        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void testPickDepositSuccess() {
        String userInput = DEPOSIT_ID + "\nexit\n";  // Введення правильного ID депозиту і завершення
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        PickDeposit pickDeposit = new PickDeposit(deposits, myDeposits);
        pickDeposit.execute();

        assertTrue(myDeposits.contains(mockDeposit));

        String output = outputStream.toString();
        assertTrue(output.contains("Депозит Test Bank обрано."));
    }

    @Test
    void testPickDepositInvalidID() {
        String userInput = "999\nexit\n";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        PickDeposit pickDeposit = new PickDeposit(deposits, myDeposits);
        pickDeposit.execute();

        assertFalse(myDeposits.contains(mockDeposit));

        String output = outputStream.toString();
        assertTrue(output.contains("Депозит із таким ID не знайдено. Спробуйте ще раз."));
    }

    @Test
    void testPickDepositInvalidInput() {
        String userInput = "invalid\nexit\n";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        PickDeposit pickDeposit = new PickDeposit(deposits, myDeposits);
        pickDeposit.execute();

        assertFalse(myDeposits.contains(mockDeposit));

        String output = outputStream.toString();
        assertTrue(output.contains("Будь ласка, введіть коректний ID депозиту (число)."));
    }




}
