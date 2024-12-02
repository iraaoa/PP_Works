package com.my_complex_lab.Commands;

import com.my_complex_lab.Deposits.Deposit;

import java.util.List;
import java.util.Scanner;

public class PickDeposit implements Command{

    private final List<Deposit> deposits;
    private final List<Deposit> myDeposits;

    public PickDeposit(List<Deposit> deposits, List<Deposit> myDeposits) {
        this.deposits = deposits;
        this.myDeposits = myDeposits;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Оберіть депозити за їх ID (введіть 'exit' для завершення):");

        for (Deposit deposit : deposits) {
            System.out.println(deposit.getDepositID() + ": " + deposit.getBankName());
        }

        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                break;
            }

                int depositID = Integer.parseInt(input);
                boolean found = false;

                for (Deposit deposit : deposits) {
                    if (deposit.getDepositID() == depositID) {
                        myDeposits.add(deposit);
                        System.out.println("Депозит " + deposit.getBankName() + " обрано.");
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    System.out.println("Депозит із таким ID не знайдено. Спробуйте ще раз.");
                }
        }
        System.out.println("Ви завершили вибір депозитів.");
    }
}
