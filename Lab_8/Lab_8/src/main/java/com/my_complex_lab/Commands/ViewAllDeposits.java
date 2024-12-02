package com.my_complex_lab.Commands;

import com.my_complex_lab.Deposits.Deposit;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ViewAllDeposits implements Command {
    private final List<Deposit> deposits;
    private static final Logger logger = LogManager.getLogger(ViewAllDeposits.class);

    public ViewAllDeposits(List<Deposit> deposits) {
        this.deposits = deposits;
    }

    @Override
    public void execute() {
        logger.info("Перегляд усіх депозитів розпочато.");

        if (deposits.isEmpty()) {
            logger.warn("Список депозитів порожній.");
            return;
        }

        int i = 1;
        for (Deposit deposit : deposits) {
            System.out.println("\n" + i + ". " + deposit);
            System.out.println(deposit);
            i++;
        }

        logger.info("Перегляд усіх депозитів завершено.");
    }
}
