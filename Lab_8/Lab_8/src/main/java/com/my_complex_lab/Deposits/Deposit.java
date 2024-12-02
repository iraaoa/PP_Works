package com.my_complex_lab.Deposits;

public abstract class Deposit {
    private int DepositID;
    private String bankName;
    private double amount;
    private double interestRate;
    private int term; // в місяцях
    private int minimalsum;
    private boolean isReplenishable; // можл поповнення
    private boolean isPartialWithdrawalAllowed; // можл часткового зняття
    private boolean isFirstReplenished = false;



    public Deposit(int DepositID, String bankName, double interestRate, int term, int minimalSum, boolean isReplenishable, boolean isPartialWithdrawalAllowed) {
        this.DepositID = DepositID;
        this.bankName = bankName;
        this.amount = 0;
        this.interestRate = interestRate;
        this.term = term;
        this.minimalsum = minimalSum;
        this.isReplenishable = isReplenishable;
        this.isPartialWithdrawalAllowed = isPartialWithdrawalAllowed;
    }


    public abstract String getDepositType();

    public int getDepositID() {
        return DepositID;
    }

    public boolean isPartialWithdrawalAllowed() {
        return isPartialWithdrawalAllowed;
    }

    public boolean isReplenishable() {
        return isReplenishable;
    }

    public String getBankName() {
        return bankName;
    }

    public double getAmount() {
        return amount;
    }

    public double getInterestRate() {
        return interestRate;
    }



    public void replenishDeposit(int amountToAdd) {
        if (isReplenishable){
            if(amountToAdd>=this.minimalsum){
                this.amount+=amountToAdd;
                System.out.println("Ваш депозит поповнено на " + amountToAdd);
            }else{
                System.out.println("Недостатня сума для мінімального поповнення");
            }
        }else{
            if((isFirstReplenished == false) && amountToAdd>=this.minimalsum){
                this.amount+=amountToAdd;
                this.isFirstReplenished = true;
                System.out.println("Ваш депозит поповнено на " + amountToAdd);
            }else if(isFirstReplenished == true){
                System.out.println("Цей депозит уже було одноразово поповнено, більше поповнення не доступне");
            }
        }
    }


    public void GetMoney(int amountToget) {
        if (isPartialWithdrawalAllowed) {
            if (amountToget <= this.amount) {
                this.amount -= amountToget;
                System.out.println("З Вашого депозиту знято " + amountToget);
            } else {
                System.out.println("У Вас замало коштів для зняття");
            }
        } else {
            System.out.println("З цього депозиту неможливо знімати кошти");
        }
    }


    @Override
    public String toString() {
        return "\nID " + DepositID +
                "\nБанк - " + bankName +
                "\nСума на рахунку - " + amount +
                "\nРічні відсотки - " + interestRate +
                "\nТермін вкладу - " + term +
                "\nМінімальна сума вкладу - " + minimalsum;

    }
}
