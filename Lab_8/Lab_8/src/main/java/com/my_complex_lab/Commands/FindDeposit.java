package com.my_complex_lab.Commands;

import com.my_complex_lab.Deposits.Deposit;
import java.util.Scanner;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FindDeposit implements Command {
    private final List<Deposit> deposits;
    private static final Logger logger = LogManager.getLogger(FindDeposit.class);

    public FindDeposit(List<Deposit> deposits) {
        this.deposits = deposits;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        logger.info("Пошук депозиту розпочато.");

        try {
            System.out.println("\nЗа яким критерієм проводити пошук?");
            System.out.println("1. По назві банку\n" +
                    "2. По типу депозиту\n");

            int type = scanner.nextInt();
            scanner.nextLine(); // Очищення буфера після введення числа

            switch (type) {
                case 1: {
                    System.out.println("\nВведіть назву банку, депозити якого хочете знайти:");
                    String bankname = scanner.nextLine();
                    logger.info("Користувач шукає депозити банку: {}", bankname);

                    boolean found = false;
                    for (Deposit deposit : deposits) {
                        if (deposit.getBankName().equalsIgnoreCase(bankname)) {
                            System.out.println(deposit);
                            found = true;
                        }
                    }
                    if (!found) {
                        logger.warn("Депозити банку '{}' не знайдені.", bankname);
                        System.out.println("\nДепозитів не знайдено.");
                    }
                    break;
                }
                case 2: {
                    System.out.println("Введіть тип депозиту, який хочете знайти:" +
                            "\nУ - для універсального" +
                            "\nО - для ощадного" +
                            "\nН - для накопичувального");
                    String type1 = scanner.nextLine();
                    logger.info("Користувач шукає депозити типу: {}", type1);

                    boolean found = false;
                    for (Deposit deposit : deposits) {
                        if (deposit.getDepositType().equalsIgnoreCase(type1)) {
                            System.out.println(deposit);
                            found = true;
                        }
                    }
                    if (!found) {
                        logger.warn("Депозити типу '{}' не знайдені.", type1);
                        System.out.println("\nДепозитів не знайдено.");
                    }
                    break;
                }
                default: {
                    logger.warn("Некоректний вибір критерію: {}", type);
                    System.out.println("Некоректний вибір. Спробуйте ще раз.");
                    break;
                }
            }
        } catch (Exception e) {
            logger.error("Сталася помилка під час пошуку депозиту.", e);
        } finally {
            logger.info("Пошук депозиту завершено.");
        }
    }
}
