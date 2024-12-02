package com.my_complex_lab.Menu;
import com.my_complex_lab.Commands.*;
import com.my_complex_lab.Deposits.Deposit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DepositManageMenu {
    final Map<Integer, Command> commands = new HashMap<>();
    private static final Logger logger = LogManager.getLogger(DepositManageMenu.class);

    public DepositManageMenu(List<Deposit> deposits, List<Deposit> myDeposits) {

        addCommand(1, new SortDeposits(deposits));
        addCommand(2, new FindDeposit(deposits));
        addCommand(3, new ViewAllDeposits(deposits));
        addCommand(4, new PickDeposit(deposits, myDeposits));
    }

    public void addCommand(int key, Command command) {
        commands.put(key, command);
    }

    public void show() {
        logger.info("Відображення меню депозитів....");

        System.out.println("\n****** Меню депозитів ******");
        System.out.println("1. Сортувати депозити");
        System.out.println("2. Знайти депозит");
        System.out.println("3. Показати усі депозити");
        System.out.println("4. Обрати депозит");
        System.out.println("0. Повернутися до попереднього меню");
    }

    public void executeCommand(int key) {
        if (commands.containsKey(key)) {
            commands.get(key).execute();
        }
    }
}
