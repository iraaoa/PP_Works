package com.my_complex_lab.Commands;

import com.my_complex_lab.Commands.PickDeposit;
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

class PickDepositTest {

    private List<Deposit> deposits;
    private List<Deposit> myDeposits;
    private Deposit mockDeposit1;
    private Deposit mockDeposit2;

    @BeforeEach
    void setUp() {
        deposits = new ArrayList<>();
        myDeposits = new ArrayList<>();

        mockDeposit1 = mock(Deposit.class);
        when(mockDeposit1.getDepositID()).thenReturn(1);
        when(mockDeposit1.getBankName()).thenReturn("Bank A");

        mockDeposit2 = mock(Deposit.class);
        when(mockDeposit2.getDepositID()).thenReturn(2);
        when(mockDeposit2.getBankName()).thenReturn("Bank B");

        deposits.add(mockDeposit1);
        deposits.add(mockDeposit2);
    }

    @Test
    void testPickDeposit() {
        String userInput = "1\n3\nexit\n";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        PickDeposit pickDeposit = new PickDeposit(deposits, myDeposits);
        pickDeposit.execute();

        // Перевірка, що депозит 1 було додано до myDeposits
        assertEquals(1, myDeposits.size());
        assertTrue(myDeposits.contains(mockDeposit1));

        // Перевірка виводу
        String output = outputStream.toString();
        assertTrue(output.contains("Оберіть депозити за їх ID"));
        assertTrue(output.contains("Депозит " + mockDeposit1.getBankName() + " обрано."));
        assertTrue(output.contains("Депозит із таким ID не знайдено. Спробуйте ще раз."));
        assertTrue(output.contains("Ви завершили вибір депозитів."));
    }
}
