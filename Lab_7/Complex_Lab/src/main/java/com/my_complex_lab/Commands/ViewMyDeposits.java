package com.my_complex_lab.Commands;

import com.my_complex_lab.Deposits.Deposit;

import java.util.List;

public class ViewMyDeposits implements Command{
    private List<Deposit> myDeposits;


    public ViewMyDeposits(List<Deposit> myDeposits) {
        this.myDeposits= myDeposits;
    }


    public void execute() {
        int i=1;

        for (Deposit deposit : myDeposits) {
            System.out.print("\n\n            "+ i);
            System.out.println(deposit);
            i++;
        }
        }
}
