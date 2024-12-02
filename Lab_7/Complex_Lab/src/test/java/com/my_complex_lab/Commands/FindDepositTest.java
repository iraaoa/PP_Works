package com.my_complex_lab.Commands;

import com.my_complex_lab.Commands.FindDeposit;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FindDepositTest {

    private List<Deposit> deposits;
    private Deposit mockDeposit1;
    private Deposit mockDeposit2;

    @BeforeEach
    void setUp() {
        deposits = new ArrayList<>();
        mockDeposit1 = mock(Deposit.class);
        when(mockDeposit1.getBankName()).thenReturn("Bank A");
        when(mockDeposit1.getDepositType()).thenReturn("У");

        mockDeposit2 = mock(Deposit.class);
        when(mockDeposit2.getBankName()).thenReturn("Bank B");
        when(mockDeposit2.getDepositType()).thenReturn("О");

        deposits.add(mockDeposit1);
        deposits.add(mockDeposit2);
    }

    @Test
    void testExecute_SearchByBankName_Found() {
        String input = "1\nBank A\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        FindDeposit findDeposit = new FindDeposit(deposits);
        findDeposit.execute();

        String output = outputStream.toString();
        assertTrue(output.contains("Пошук депозиту...."));
        assertTrue(output.contains(mockDeposit1.toString()));
    }

    @Test
    void testExecute_SearchByBankName_NotFound() {
        String input = "1\nBank C\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        FindDeposit findDeposit = new FindDeposit(deposits);
        findDeposit.execute();

        String output = outputStream.toString();
        assertTrue(output.contains("Депозитів не знайдено"));
    }

    @Test
    void testExecute_SearchByDepositType_Found() {
        String input = "2\nУ\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        FindDeposit findDeposit = new FindDeposit(deposits);
        findDeposit.execute();

        String output = outputStream.toString();
        assertTrue(output.contains("Пошук депозиту...."));
        assertTrue(output.contains(mockDeposit1.toString()));
    }

    @Test
    void testExecute_SearchByDepositType_NotFound() {
        String input = "2\nН\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        FindDeposit findDeposit = new FindDeposit(deposits);
        findDeposit.execute();

        String output = outputStream.toString();
        assertTrue(output.contains("Депозитів не знайдено"));
    }
}
