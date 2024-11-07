package com.my_complex_lab.Deposits;

public class AccumulatingDeposit extends Deposit{ //НАКОПИЧУВАЛЬНИЙ


    public AccumulatingDeposit(int DepositID, String bankName, double interestRate, int term, int minimalsum) {
        super(DepositID, bankName, interestRate, term, minimalsum, true, false);
    }


    @Override
    public String getDepositType() {
        return "Н";
    }


    @Override
    public String toString() {
        return "\n--------------------------------" +
                "\nНакопичувальний Депозит" +
                super.toString() +
                "\nМожливість поповнення - так" +
                "\nМожливість часткового зняття - ні";
    }

}
