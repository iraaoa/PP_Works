package com.my_complex_lab.Commands;

import com.my_complex_lab.Deposits.Deposit;

import java.util.List;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WithdrawEarly implements Command {

    private final List<Deposit> myDeposits;
    static Logger logger = LogManager.getLogger(WithdrawEarly.class);

    public WithdrawEarly(List<Deposit> myDeposits) {
        this.myDeposits = myDeposits;
    }

    @Override
    public void execute() {
        logger.info("Початок процедури дострокового зняття коштів.");

        if (myDeposits.isEmpty()) {
            logger.warn("Список депозитів порожній. Немає депозитів для зняття коштів.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть ID депозиту, з якого хочете зняти кошти: ");

        try {
            int id = scanner.nextInt();
            boolean found = false;

            for (Deposit deposit : myDeposits) {
                if (deposit.getDepositID() == id) {
                    System.out.println("Поточний депозит: ");
                    System.out.println(deposit);
                    logger.info("Знайдено депозит з ID {}: {}", id, deposit);
                    found = true;

                    System.out.print("Введіть суму для зняття: ");
                    int amountToGet = scanner.nextInt();

                    if (amountToGet <= 0) {
                        System.out.println("Сума для зняття має бути позитивною.");
                        logger.warn("Спроба зняти недопустиму суму: {}", amountToGet);
                        return;
                    }

                    try {
                        deposit.GetMoney(amountToGet);
                        System.out.println("Оновлений депозит: ");
                        System.out.println(deposit);
                        logger.info("Успішно знято {} з депозиту з ID {}.", amountToGet, id);
                    } catch (IllegalArgumentException e) {
                        logger.error("Помилка зняття коштів: {}", e.getMessage());
                    }
                    break;
                }
            }

            if (!found) {
                System.out.println("У вас немає депозиту з таким ID.");
                logger.warn("Не знайдено депозит з ID {}.", id);
            }

        } catch (Exception e) {
            System.out.println("Помилка вводу. Будь ласка, введіть коректний ID.");
            logger.error("Помилка вводу під час зняття коштів: {}", e.getMessage());
        }

        logger.info("Процедура дострокового зняття коштів завершена.");
    }
}
