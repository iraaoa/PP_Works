package com.my_complex_lab.Commands;

import com.my_complex_lab.Deposits.Deposit;

import java.util.List;
import java.util.Scanner;

public class ReplenishDeposit implements Command{

    private List<Deposit> myDeposits;


    public ReplenishDeposit(List<Deposit> myDeposits) {
        this. myDeposits= myDeposits;
    }
    @Override
    public void execute() {
        System.out.println("Поповнення.");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть ID депозиту, який хочете поповнити: ");
        int id=scanner.nextInt();
        int found =0;
        for (Deposit deposit : myDeposits) {
            if (deposit.getDepositID() == id) {
                System.out.println("Поточний депозит: ");
                System.out.println(deposit);
                found=1;
                System.out.print("Введіть суму для поповнення: ");
                int amountToAdd = scanner.nextInt();

                deposit.replenishDeposit(amountToAdd);
                System.out.println("Оновлений депозит: ");
                System.out.println(deposit);
                break;
            }
        }
        if(found==0){
            System.out.println("У вас немає депозиту з таким ID");
        }
    }
}
