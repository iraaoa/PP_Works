package com.my_complex_lab.Menu;

import com.my_complex_lab.Commands.Command;
import com.my_complex_lab.Deposits.Deposit;
import com.my_complex_lab.Menu.MyDepositsMenu;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MyDepositMenuTest {

    @Test
    void testShow() {
        List<Deposit> deposits = new ArrayList<>();
        MyDepositsMenu menu = new MyDepositsMenu(deposits);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        menu.show();

        String expectedOutput = "****** Меню моїх депозитів ******" +
                "\n1. Достроково зняти депозит" +
                "\n2. Поповнити депозит" +
                "\n3. Переглянути мої депозити" +
                "\n0. Повернутися до головного меню";

        String actualOutput = outputStream.toString().replace("\r\n", "\n").trim();

        System.setOut(System.out);

        assertEquals(expectedOutput, actualOutput, "Метод show має коректно виводити меню");
    }

    @Test
    void testAddCommand() {
        List<Deposit> deposits = new ArrayList<>();
        MyDepositsMenu menu = new MyDepositsMenu(deposits);
        Command mockCommand = mock(Command.class);

        menu.addCommand(1, mockCommand);

        assertTrue(menu.commands.containsKey(1), "Команда має бути додана до мапи команд");
        assertEquals(mockCommand, menu.commands.get(1), "Команда має відповідати доданій команді");
    }

    @Test
    void testExecuteCommandWithValidKey() {
        List<Deposit> deposits = new ArrayList<>();
        MyDepositsMenu menu = new MyDepositsMenu(deposits);
        Command mockCommand = mock(Command.class);

        menu.addCommand(1, mockCommand);
        menu.executeCommand(1);

        verify(mockCommand, times(1)).execute();
    }
}
