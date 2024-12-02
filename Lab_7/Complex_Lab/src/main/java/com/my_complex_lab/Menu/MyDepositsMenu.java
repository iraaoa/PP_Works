package com.my_complex_lab.Menu;
import com.my_complex_lab.Commands.*;
import com.my_complex_lab.Deposits.Deposit;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import com.my_complex_lab.Commands.Command;


public class MyDepositsMenu {
    public final Map<Integer, Command> commands = new HashMap<>();


    public void addCommand(int key, Command command) {
        commands.put(key, command);
    }


    public MyDepositsMenu(List<Deposit> myDeposits) {
        addCommand(1, new WithdrawEarly(myDeposits));
        addCommand(2, new ReplenishDeposit(myDeposits));
        addCommand(3, new ViewMyDeposits(myDeposits));
    }


    public void show() {
        System.out.println("\n****** Меню моїх депозитів ******");
        System.out.println("1. Достроково зняти депозит");
        System.out.println("2. Поповнити депозит");
        System.out.println("3. Переглянути мої депозити");
        System.out.println("0. Повернутися до головного меню");
    }

    public void executeCommand(int key) {
        if (commands.containsKey(key)) {
            commands.get(key).execute();
        }
    }
}


