package com.my_complex_lab.Deposits;

public class UniversalDeposit extends Deposit {

    public UniversalDeposit(int DepositID, String bankName, double interestRate, int term, int minimalsum) {
        super(DepositID, bankName, interestRate, term, minimalsum, true, true);
    }

    @Override
    public String getDepositType() {
        return "У";
    }



    @Override
    public String toString() {
        return "\n--------------------------------" +
                "\nУніверсальний Депозит" +
                super.toString() +
                "\nМожливість поповнення - так" +
                "\nМожливість часткового зняття - так";
    }
}
