package com.my_complex_lab;

import com.my_complex_lab.Deposits.Deposit;
import com.my_complex_lab.Menu.DepositManageMenu;
import com.my_complex_lab.Menu.Menu;
import com.my_complex_lab.Commands.*;
import com.my_complex_lab.Menu.MyDepositsMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {


    private static  List<Deposit> deposits = new ArrayList<>();
    private static List<Deposit> myDeposits = new ArrayList<>();

    public static void main(String[] args) {

        Menu menu = new Menu();
        Scanner scanner = new Scanner(System.in);
        menu.addCommand(1, new LoadDeposit(deposits));
        menu.addCommand(2, () -> showDepositsManageMenu(scanner));
        menu.addCommand(3, () -> showMyDepositsMenu(scanner));
        menu.addCommand(0, () -> System.exit(0));

        int choice;
        do {
            menu.show();
            System.out.print("\nВаш вибір: ");
            choice = scanner.nextInt();
            menu.executeCommand(choice);
        } while (choice != 0);
    }


    private static void showMyDepositsMenu(Scanner scanner) {
        MyDepositsMenu myDepositsMenu = new MyDepositsMenu(myDeposits);
        while (true) {
            myDepositsMenu.show();
            System.out.print("Введіть номер команди: ");
            int choice = scanner.nextInt();
            if (choice == 0) {
                break;
            }
            myDepositsMenu.executeCommand(choice);
        }
    }

    private static void showDepositsManageMenu(Scanner scanner) {
        DepositManageMenu depositManageMenu = new DepositManageMenu(deposits, myDeposits);

        while (true) {
            depositManageMenu.show();
            System.out.print("Введіть номер команди: ");
            int choice = scanner.nextInt();
            if (choice == 0) {
                break;
            }
            depositManageMenu.executeCommand(choice);
        }
    }

}

