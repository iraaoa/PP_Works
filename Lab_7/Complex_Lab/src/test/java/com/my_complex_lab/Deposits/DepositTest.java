package com.my_complex_lab.Deposits;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



class DepositTest {

    @Test
    void replenishDepositTest() {
        Deposit deposit = new AccumulatingDeposit(1, "TestBank", 5.0, 12, 1000);
        deposit.replenishDeposit(1500);
        assertEquals(1500, deposit.getAmount(), "Сума депозита має бути 1500 після поповнення");
    }

    @Test
    void replenishDepositTest2() {
        Deposit deposit = new AccumulatingDeposit(1, "TestBank", 5.0, 12, 1000);
        deposit.replenishDeposit(50);
        assertEquals(0, deposit.getAmount(), "Сума депозита має бути більше 1000");
    }


    @Test
    void replenishDepositTest4() {
        Deposit deposit = new SavingDeposit(1, "TestBank", 5.0, 12, 1000);
        deposit.replenishDeposit(15000);
        deposit.replenishDeposit(50);
        assertEquals(15000, deposit.getAmount(), "Недостатня сума");
    }

    @Test
    void isPartialWithdrawalAllowed(){
        Deposit deposit = new SavingDeposit(1, "Test Bank", 5.0, 10, 1000);
        assertEquals(false, deposit.isPartialWithdrawalAllowed(), "має бути false");
    }

    @Test
    void isReplenishable(){
        Deposit deposit = new SavingDeposit(1, "Test Bank", 5.0, 10, 1000);
        assertEquals(false, deposit.isReplenishable(), "має бути false");
    }

    @Test
    void getDepositID(){
        Deposit deposit = new SavingDeposit(1, "Test Bank", 5.0, 10, 1000);
        assertEquals(1, deposit.getDepositID(), "ID депозиту має бути 1");
    }


    @Test
    void getInterestRate(){
        Deposit deposit = new SavingDeposit(1, "Test Bank", 5.0, 10, 1000);
        assertEquals(5.0, deposit.getInterestRate(), "InterestRate депозиту має бути 5.0");
    }



    @Test
    void getBankName(){
        Deposit deposit = new SavingDeposit(2, "Private", 5.0, 10, 1000);
        assertEquals("Private", deposit.getBankName(), "Імя депозиту має бути Private");
    }

    @Test
    void toStringAcTest() {
        Deposit deposit = new AccumulatingDeposit(1, "TestBank", 5.0, 12, 1000);
        String expectedString = "\n--------------------------------" +
                "\nНакопичувальний Депозит" +
                "\nID 1" +
                "\nБанк - TestBank" +
                "\nСума на рахунку - 0.0" +
                "\nРічні відсотки - 5.0" +
                "\nТермін вкладу - 12" +
                "\nМінімальна сума вкладу - 1000" +
                "\nМожливість поповнення - так" +
                "\nМожливість часткового зняття - ні";
        assertEquals(expectedString, deposit.toString(), "toString має коректно повертати опис депозиту");
    }



    @Test
    void toStringSTest() {
        Deposit deposit = new SavingDeposit(1, "TestBank", 5.0, 12, 1000);
        String expectedString = "\n--------------------------------" +
                "\nОщадний Депозит" +
                "\nID 1" +
                "\nБанк - TestBank" +
                "\nСума на рахунку - 0.0" +
                "\nРічні відсотки - 5.0" +
                "\nТермін вкладу - 12" +
                "\nМінімальна сума вкладу - 1000" +
                "\nМожливість поповнення - ні" +
                "\nМожливість часткового зняття - ні";
        assertEquals(expectedString, deposit.toString(), "toString має коректно повертати опис депозиту");
    }


    @Test
    void toStringUTest() {
        Deposit deposit = new UniversalDeposit(1, "TestBank", 5.0, 12, 1000);
        String expectedString = "\n--------------------------------" +
                "\nУніверсальний Депозит" +
                "\nID 1" +
                "\nБанк - TestBank" +
                "\nСума на рахунку - 0.0" +
                "\nРічні відсотки - 5.0" +
                "\nТермін вкладу - 12" +
                "\nМінімальна сума вкладу - 1000" +
                "\nМожливість поповнення - так" +
                "\nМожливість часткового зняття - так";
        assertEquals(expectedString, deposit.toString(), "toString має коректно повертати опис депозиту");
    }

    @Test
    void testGetMoneyWithNoPartialWithdrawalAllowed() {
        Deposit deposit = new SavingDeposit(1, "Test Bank", 5.0, 10, 1000);
        deposit.replenishDeposit(2000);
        deposit.GetMoney(1500);

        assertEquals(2000, deposit.getAmount(), "З цього депозиту не можна знімати кошти");
    }


    @Test
    void testGetMoneySuccessfulWithdrawalAllowed() {
        Deposit deposit = new UniversalDeposit(1, "Test Bank", 5.0, 12, 1000);
        deposit.replenishDeposit(5000);
        deposit.GetMoney(3000);

        assertEquals(2000, deposit.getAmount(), "Сума на рахунку має бути 2000 після зняття 3000");
    }


    @Test
    void NoMoney() {
        Deposit deposit = new UniversalDeposit(1, "Test Bank", 5.0, 12, 1000);
        deposit.replenishDeposit(5000);
        deposit.GetMoney(6000);

        assertEquals(5000, deposit.getAmount(), "У вас мало коштів");
    }

    @Test
    void getDepositTypeTest() {
        Deposit deposit = new AccumulatingDeposit(1, "TestBank", 5.0, 12, 1000);
        assertEquals("Н", deposit.getDepositType(), "Тип депозиту має бути Накопичувальний");
    }


    @Test
    void savingDepositTest() {
        Deposit savingDeposit = new SavingDeposit(2, "AnotherBank", 4.0, 24, 500);
        assertEquals("О", savingDeposit.getDepositType(), "Тип депозиту має бути Ощадний");
    }

    @Test
    void universalDepositTest() {
        Deposit universalDeposit = new UniversalDeposit(3, "UniversalBank", 6.0, 36, 1500);
        assertEquals("У", universalDeposit.getDepositType(), "Тип депозиту має бути Універсальний");
    }



}
