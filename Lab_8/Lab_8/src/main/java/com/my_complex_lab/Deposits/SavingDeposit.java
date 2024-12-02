package com.my_complex_lab.Deposits;

public class SavingDeposit extends Deposit{ //Ощадний


    public SavingDeposit(int DepositID, String bankName, double interestRate, int term, int minimalsum) {
        super(DepositID, bankName, interestRate, term, minimalsum, false, false);
    }

    @Override
    public String getDepositType() {
        return "О";
    }


    @Override
    public String toString() {
        return "\n--------------------------------" +
                "\nОщадний Депозит" +
                super.toString() +
                "\nМожливість поповнення - ні" +
                "\nМожливість часткового зняття - ні";
    }
}
