package com.my_complex_lab.Menu;
import com.my_complex_lab.Commands.Command;
import java.util.HashMap;
import java.util.Map;


public class Menu {
    final Map<Integer, Command> commands = new HashMap<>();

    public void addCommand(int key, Command command) {
        commands.put(key, command);
    }

    public void show() {
        System.out.println("\n****** Головне меню ******");
        System.out.println("1. Завантажити депозити з файлу");
        System.out.println("2. Переглянути усі депозити");
        System.out.println("3. Мої депозити");
        System.out.println("0. Вийти");
    }

    public void executeCommand(int key) {
        if (commands.containsKey(key)) {
            commands.get(key).execute();
        }
    }
}
