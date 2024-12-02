package com.my_complex_lab.Commands;

import com.my_complex_lab.Deposits.Deposit;

import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReplenishDeposit implements Command {

    private static final Logger logger = LogManager.getLogger(ReplenishDeposit.class);
    private List<Deposit> myDeposits;

    public ReplenishDeposit(List<Deposit> myDeposits) {
        this.myDeposits = myDeposits;
    }

    @Override
    public void execute() {
        logger.info("Початок процесу поповнення депозиту.");
        System.out.println("Поповнення.");
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Введіть ID депозиту, який хочете поповнити: ");
            int id = scanner.nextInt();
            boolean found = false;

            for (Deposit deposit : myDeposits) {
                if (deposit.getDepositID() == id) {
                    logger.info("Депозит з ID {} знайдено.", id);
                    System.out.println("Поточний депозит: ");
                    System.out.println(deposit);
                    found = true;

                    System.out.print("Введіть суму для поповнення: ");
                    int amountToAdd = scanner.nextInt();

                    // Перевірка на валідність введеної суми
                    if (amountToAdd <= 0) {
                        logger.warn("Спроба поповнення депозиту некоректною сумою: {}.", amountToAdd);
                        return;
                    }

                    deposit.replenishDeposit(amountToAdd);
                    logger.info("Депозит з ID {} успішно поповнено на суму {}.", id, amountToAdd);
                    System.out.println("Оновлений депозит: ");
                    System.out.println(deposit);
                    break;
                }
            }

            if (!found) {
                logger.warn("Депозит з ID {} не знайдено серед доступних.", id);
            }

        } catch (NumberFormatException e) {
            logger.error("Некоректний формат вводу числа.", e);
        } catch (Exception e) {
            logger.error("Сталася помилка під час поповнення депозиту.", e);
        } finally {
            logger.info("Завершення процесу поповнення депозиту.");
        }
    }
}
