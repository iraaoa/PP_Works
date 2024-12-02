package com.my_complex_lab.Commands;

import com.my_complex_lab.Commands.ViewMyDeposits;
import com.my_complex_lab.Deposits.Deposit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ViewMyDepositsTest {

    private List<Deposit> deposits;
    private Deposit mockDeposit1;
    private Deposit mockDeposit2;

    @BeforeEach
    void setUp() {
        deposits = new ArrayList<>();

        // Створюємо моки для депозитів
        mockDeposit1 = mock(Deposit.class);
        when(mockDeposit1.toString()).thenReturn("Deposit 1: Bank A, Type U, Balance 1000");

        mockDeposit2 = mock(Deposit.class);
        when(mockDeposit2.toString()).thenReturn("Deposit 2: Bank B, Type О, Balance 2000");

        deposits.add(mockDeposit1);
        deposits.add(mockDeposit2);
    }

    @Test
    void testExecute_ViewDepositsOutput() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        ViewMyDeposits viewMyDeposits = new ViewMyDeposits(deposits);
        viewMyDeposits.execute();

        String output = outputStream.toString().replaceAll("\r\n", "\n").trim(); // Заміна переносу рядка

        System.out.println(output);

        assertTrue(output.contains("Deposit 1: Bank A, Type U, Balance 1000"));
        assertTrue(output.contains("Deposit 2: Bank B, Type О, Balance 2000"));
    }
}
