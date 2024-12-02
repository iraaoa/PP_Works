package com.my_complex_lab.Commands;

import com.my_complex_lab.Commands.SortDeposits;
import com.my_complex_lab.Deposits.Deposit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class SortDepositsTest {

    private List<Deposit> deposits;
    private Deposit mockDeposit1;
    private Deposit mockDeposit2;
    private Deposit mockDeposit3;

    @BeforeEach
    void setUp() {
        deposits = new ArrayList<>();

        // моки для депозитів з різними процентними ставками
        mockDeposit1 = mock(Deposit.class);
        when(mockDeposit1.getInterestRate()).thenReturn(5.0);
        when(mockDeposit1.toString()).thenReturn("Deposit 1: Bank A, Rate 5.0%");

        mockDeposit2 = mock(Deposit.class);
        when(mockDeposit2.getInterestRate()).thenReturn(3.5);
        when(mockDeposit2.toString()).thenReturn("Deposit 2: Bank B, Rate 3.5%");

        mockDeposit3 = mock(Deposit.class);
        when(mockDeposit3.getInterestRate()).thenReturn(4.0);
        when(mockDeposit3.toString()).thenReturn("Deposit 3: Bank C, Rate 4.0%");

        deposits.add(mockDeposit1);
        deposits.add(mockDeposit2);
        deposits.add(mockDeposit3);
    }

    @Test
    void testSortDeposits() {
        // Перехоплення виводу в консоль
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        SortDeposits sortDeposits = new SortDeposits(deposits);
        sortDeposits.execute();

        assertTrue(deposits.get(0).getInterestRate() <= deposits.get(1).getInterestRate());
        assertTrue(deposits.get(1).getInterestRate() <= deposits.get(2).getInterestRate());

        String output = outputStream.toString().replaceAll("\r\n", "\n").trim();
        System.out.println(output); // Додаємо виведення фактичного результату

        assertTrue(output.contains("Депозити відсортовано за процентною ставкою:"));
        assertTrue(output.contains("Deposit 2: Bank B, Rate 3.5%"));
        assertTrue(output.contains("Deposit 3: Bank C, Rate 4.0%"));
        assertTrue(output.contains("Deposit 1: Bank A, Rate 5.0%"));
    }
}
