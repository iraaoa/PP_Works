package com.my_complex_lab.Commands;

import com.my_complex_lab.Commands.WithdrawEarly;
import com.my_complex_lab.Deposits.Deposit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class WithdrawEarlyTest {

    private List<Deposit> deposits;
    private Deposit mockDeposit1;
    private Deposit mockDeposit2;

    @BeforeEach
    void setUp() {
        deposits = new ArrayList<>();
        mockDeposit1 = mock(Deposit.class);
        mockDeposit2 = mock(Deposit.class);

        when(mockDeposit1.getDepositID()).thenReturn(1);
        when(mockDeposit2.getDepositID()).thenReturn(2);

        deposits.add(mockDeposit1);
        deposits.add(mockDeposit2);
    }

    @Test
    void testExecute_ValidDepositId() {
        WithdrawEarly withdrawEarly = new WithdrawEarly(deposits);

        String input = "1\n500\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        withdrawEarly.execute();

        verify(mockDeposit1, times(1)).GetMoney(500);
        verify(mockDeposit2, never()).GetMoney(anyInt());

        String output = outputStream.toString();
        assertTrue(output.contains("Знімання коштів."));
        assertTrue(output.contains("Введіть ID депозиту, з якого хочете зняти кошти:"));
        assertTrue(output.contains("Оновлений депозит:"));
    }



    @Test
    void testExecute_InvalidDepositId() {
        WithdrawEarly withdrawEarly = new WithdrawEarly(deposits);

        String input = "3\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        withdrawEarly.execute();

        String output = outputStream.toString();
        assertTrue(output.contains("У вас немає депозиту з таким ID"));

        verify(mockDeposit1, never()).GetMoney(anyInt());
        verify(mockDeposit2, never()).GetMoney(anyInt());
    }
}
