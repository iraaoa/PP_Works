package com.my_complex_lab.Commands;

import com.my_complex_lab.Deposits.Deposit;
import java.util.Scanner;
import java.util.List;

public class FindDeposit implements Command {
    private List<Deposit> deposits;

    public FindDeposit(List<Deposit> deposits) {
        this.deposits = deposits;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        int type;
        System.out.println("Пошук депозиту....");

        System.out.println("\nЗа яким критерієм проводити пошук?");
        System.out.println("1. По назві банку\n" +
                "2. По типу депозиту\n" +
                "3. Можливість поповнення\n" +
                "4. Можливість часткового знімання коштів");

        type = scanner.nextInt();
        scanner.nextLine();

        switch (type) {
            case 1: {
                System.out.println("\nВведіть назву банку, депозити якого хочете знайти");
                String bankname = scanner.nextLine();
                boolean found = false;
                for (Deposit deposit : deposits) {
                    if (deposit.getBankName().equalsIgnoreCase(bankname)) {
                        System.out.println(deposit);
                        found = true;
                    }
                }
                if (!found) {
                    System.out.println("\nДепозитів не знайдено");
                }
                break;
            }
            case 2: {
                System.out.println("Введіть тип депозиту, який хочете знайти" +
                        "\nУ - для універсального" +
                        "\nО - для ощадного" +
                        "\nН - для накопичувального");
                String type1 = scanner.nextLine();
                boolean found = false;

                for (Deposit deposit : deposits) {
                    if (deposit.getDepositType().equalsIgnoreCase(type1)) {
                        System.out.println(deposit);
                        found = true;
                    }
                }
                if (!found) {
                    System.out.println("\nДепозитів не знайдено");
                }
                break;
            }


            case 3: {
                boolean found = false;
                for (Deposit deposit : deposits) {
                    if (deposit.isReplenishable()) {
                        System.out.println(deposit);
                        found = true;
                    }
                }
                if (!found) {
                    System.out.println("\nДепозитів не знайдено");
                }
                break;
            }

            case 4: {
                boolean found = false;
                for (Deposit deposit : deposits) {
                    if (deposit.isPartialWithdrawalAllowed()) {
                        System.out.println(deposit);
                        found = true;
                    }
                }
                if (!found) {
                    System.out.println("\nДепозитів не знайдено");
                }
                break;
            }



        }
    }
}
