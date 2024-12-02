package com.my_complex_lab.Menu;

import com.my_complex_lab.Commands.*;
import com.my_complex_lab.Deposits.Deposit;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.my_complex_lab.Commands.Command;

public class MyDepositsMenu {
    final Map<Integer, Command> commands = new HashMap<>();
    private static final Logger logger = LogManager.getLogger(MyDepositsMenu.class); // Логер для MyDepositsMenu

    // Конструктор, який додає команди
    public MyDepositsMenu(List<Deposit> myDeposits) {
        addCommand(1, new WithdrawEarly(myDeposits));
        addCommand(2, new ReplenishDeposit(myDeposits));
        addCommand(3, new ViewMyDeposits(myDeposits));
    }

    // Додаємо команду до меню
    public void addCommand(int key, Command command) {
        commands.put(key, command);
    }

    // Показуємо меню
    public void show() {
        System.out.println("\n****** Меню моїх депозитів ******");
        System.out.println("1. Достроково зняти депозит");
        System.out.println("2. Поповнити депозит");
        System.out.println("3. Переглянути мої депозити");
        System.out.println("0. Повернутися до головного меню");
        logger.info("Меню депозитів показано користувачеві");
    }

    // Виконуємо команду
    public void executeCommand(int key) {
        if (commands.containsKey(key)) {
            commands.get(key).execute();
        }
    }
}
