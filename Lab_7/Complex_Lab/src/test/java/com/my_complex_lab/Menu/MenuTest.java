package com.my_complex_lab.Menu;
import com.my_complex_lab.Commands.Command;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MenuTest {

    @Test
    void testShow() {
        Menu menu = new Menu();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        menu.show();

        String expectedOutput = "****** Головне меню ******\n" +
                "1. Завантажити депозити з файлу\n" +
                "2. Переглянути усі депозити\n" +
                "3. Мої депозити\n" +
                "0. Вийти";

        String actualOutput = outputStream.toString().replace("\r\n", "\n").trim();

        System.setOut(System.out);

        assertEquals(expectedOutput, actualOutput, "Метод show має коректно виводити меню");
    }



    @Test
    void testAddCommand() {
        Menu menu = new Menu();
        Command mockCommand = new MockCommand();

        menu.addCommand(1, mockCommand);

        assertTrue(menu.commands.containsKey(1), "Команда має бути додана до мапи команд");
        assertEquals(mockCommand, menu.commands.get(1), "Команда має відповідати доданій команді");
    }

    @Test
    void testExecuteCommandWithValidKey() {
        Menu menu = new Menu();
        Command mockCommand = mock(Command.class);

        menu.addCommand(1, mockCommand);

        menu.executeCommand(1);

        verify(mockCommand, times(1)).execute();
    }

}
