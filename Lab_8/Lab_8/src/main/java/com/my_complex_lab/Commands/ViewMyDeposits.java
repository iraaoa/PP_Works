package com.my_complex_lab.Commands;

import com.my_complex_lab.Deposits.Deposit;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ViewMyDeposits implements Command {
    private final List<Deposit> myDeposits;
    private static final Logger logger = LogManager.getLogger(ViewMyDeposits.class);

    public ViewMyDeposits(List<Deposit> myDeposits) {
        this.myDeposits = myDeposits;
    }

    @Override
    public void execute() {
        logger.info("Перегляд власних депозитів розпочато.");

        if (myDeposits.isEmpty()) {
            logger.warn("Список ваших депозитів порожній.");
            return;
        }

        int i = 1;
        for (Deposit deposit : myDeposits) {
            System.out.println("\n" + i + ". " + deposit);
            logger.info("Відображено депозит №{}: {}", i, deposit);
            i++;
        }

        logger.info("Перегляд власних депозитів завершено.");
    }
}
