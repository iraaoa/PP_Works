package com.my_complex_lab.Commands;

import com.my_complex_lab.Deposits.Deposit;
import java.util.List;

public class ViewAllDeposits implements Command {
    private List<Deposit> deposits;

    public ViewAllDeposits(List<Deposit> deposits) {
        this.deposits = deposits;
    }

        @Override
        public void execute() {
            int i=1;

            for (Deposit deposit : deposits) {
                System.out.print("\n\n            "+ i);
                System.out.println(deposit);
                i++;
            }
        }
    }
