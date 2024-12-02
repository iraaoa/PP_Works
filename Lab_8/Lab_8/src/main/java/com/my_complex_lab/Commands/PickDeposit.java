package com.my_complex_lab.Commands;

import com.my_complex_lab.Deposits.Deposit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Scanner;

public class PickDeposit implements Command{

    private final List<Deposit> deposits;
    private final List<Deposit> myDeposits;
    private static final Logger logger = LogManager.getLogger(PickDeposit.class);

    public PickDeposit(List<Deposit> deposits, List<Deposit> myDeposits) {
        this.deposits = deposits;
        this.myDeposits = myDeposits;
    }

    @Override
    public void execute() {
        logger.info("Обираємо депозити...");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Оберіть депозити за їх ID (введіть 'exit' для завершення):");

        for (Deposit deposit : deposits) {
            System.out.println(deposit.getDepositID() + ": " + deposit.getBankName());
        }

        while (true) {
            try {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("exit")) {
                    break;
                }

                // Спроба перетворити введений текст у число
                int depositID = Integer.parseInt(input);
                boolean found = false;

                for (Deposit deposit : deposits) {
                    if (deposit.getDepositID() == depositID) {
                        myDeposits.add(deposit);
                        System.out.println("Депозит " + deposit.getBankName() + " обрано.");
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    System.out.println("Депозит із таким ID не знайдено. Спробуйте ще раз.");
                }

            } catch (NumberFormatException e) {
                // Обробка помилки неправильного введення числа
                logger.error("Некоректне введення: очікується число.", e);
                System.out.println("Будь ласка, введіть коректний ID депозиту (число).");
            } catch (Exception e) {
                // Загальний виняток для інших помилок
                logger.error("Сталася помилка під час вибору депозиту.", e);
            }
        }

        logger.info("\nВи завершили вибір депозитів.");
    }

}
