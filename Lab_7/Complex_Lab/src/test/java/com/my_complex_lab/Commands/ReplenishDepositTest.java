package com.my_complex_lab.Commands;

import com.my_complex_lab.Commands.ReplenishDeposit;
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

class ReplenishDepositTest {

    private List<Deposit> deposits;
    private Deposit mockDeposit1;
    private Deposit mockDeposit2;

    @BeforeEach
    void setUp() {
        deposits = new ArrayList<>();

        mockDeposit1 = mock(Deposit.class);
        when(mockDeposit1.getDepositID()).thenReturn(1);
        when(mockDeposit1.toString()).thenReturn("Deposit 1: Bank A, Type U, Balance 1000");

        mockDeposit2 = mock(Deposit.class);
        when(mockDeposit2.getDepositID()).thenReturn(2);
        when(mockDeposit2.toString()).thenReturn("Deposit 2: Bank B, Type О, Balance 2000");

        deposits.add(mockDeposit1);
        deposits.add(mockDeposit2);
    }

    @Test
    void testReplenishDeposit_Success() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));


        System.setIn(new java.io.ByteArrayInputStream("1\n500\n".getBytes()));

        ReplenishDeposit replenishDeposit = new ReplenishDeposit(deposits);
        replenishDeposit.execute();

        verify(mockDeposit1, times(1)).replenishDeposit(500);

        String output = outputStream.toString().replaceAll("\r\n", "\n").trim();
        System.out.println(output);

        assertTrue(output.contains("Поповнення."));
        assertTrue(output.contains("Поточний депозит:"));
        assertTrue(output.contains("Оновлений депозит:"));
        assertTrue(output.contains("Deposit 1: Bank A, Type U, Balance 1000"));
    }

    @Test
    void testReplenishDeposit_Failure() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        System.setIn(new java.io.ByteArrayInputStream("3\n500\n".getBytes()));

        ReplenishDeposit replenishDeposit = new ReplenishDeposit(deposits);
        replenishDeposit.execute();

        String output = outputStream.toString().replaceAll("\r\n", "\n").trim();
        System.out.println(output);

        assertTrue(output.contains("У вас немає депозиту з таким ID"));
    }
}
