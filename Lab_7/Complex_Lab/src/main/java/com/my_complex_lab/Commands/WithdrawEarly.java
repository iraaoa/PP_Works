package com.my_complex_lab.Commands;

import com.my_complex_lab.Deposits.Deposit;

import java.util.List;
import java.util.Scanner;

public class WithdrawEarly implements Command{


    private List<Deposit> myDeposits;


    public WithdrawEarly(List<Deposit> myDeposits) {
        this. myDeposits= myDeposits;
    }

    @Override
    public void execute() {
        System.out.println("Знімання коштів.");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть ID депозиту, з якого хочете зняти кошти: ");
        int id=scanner.nextInt();
        int found =0;
        for (Deposit deposit : myDeposits) {
            if (deposit.getDepositID() == id) {
                System.out.println("Поточний депозит: ");
                System.out.println(deposit);
                found=1;
                System.out.print("Введіть суму для зняття: ");
                int amountToGet = scanner.nextInt();

                deposit.GetMoney(amountToGet);
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