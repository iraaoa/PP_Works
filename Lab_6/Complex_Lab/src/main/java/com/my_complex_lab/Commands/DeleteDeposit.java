package com.my_complex_lab.Commands;

import com.my_complex_lab.Deposits.Deposit;

import java.util.List;
import java.util.Scanner;

public class DeleteDeposit implements Command{


    private List<Deposit> myDeposits;


    public DeleteDeposit(List<Deposit> myDeposits) {
        this. myDeposits= myDeposits;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Введіть ID депозиту, який ви хочете видалити, або 0 для завершення: ");
            int id = scanner.nextInt();

            if (id == 0) {
                break;
            }

            boolean found = false;
            for (int i = 0; i < myDeposits.size(); i++) {
                if (myDeposits.get(i).getDepositID() == id) {
                    myDeposits.remove(i);
                    found = true;
                    System.out.println("Депозит видалено.");
                    break;
                }
            }

            if (!found) {
                System.out.println("Депозит з таким ID не знайдено.");
            }
        }
    }
}
