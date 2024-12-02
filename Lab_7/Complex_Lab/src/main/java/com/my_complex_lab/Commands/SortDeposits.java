package com.my_complex_lab.Commands;

import com.my_complex_lab.Deposits.Deposit;
import java.util.List;

import java.util.Collections;
import java.util.Comparator;

public class SortDeposits implements Command {
    private List<Deposit> deposits;

    public SortDeposits(List<Deposit> deposits) {
        this.deposits = deposits;
    }

    @Override
    public void execute() {
        Collections.sort(deposits, new Comparator<Deposit>() {

            @Override
            public int compare(Deposit d1, Deposit d2) {
                return Double.compare(d1.getInterestRate(), d2.getInterestRate());
            }
        });

        System.out.println("Депозити відсортовано за процентною ставкою:");
        for (Deposit deposit : deposits) {
            System.out.println(deposit);
        }
    }
}
