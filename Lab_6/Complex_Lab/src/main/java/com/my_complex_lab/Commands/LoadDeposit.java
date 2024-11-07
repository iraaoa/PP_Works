package com.my_complex_lab.Commands;

import com.my_complex_lab.Deposits.AccumulatingDeposit;
import com.my_complex_lab.Deposits.Deposit;
import com.my_complex_lab.Deposits.SavingDeposit;
import com.my_complex_lab.Deposits.UniversalDeposit;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;



public class LoadDeposit implements Command {
    private List<Deposit> deposits;

    public LoadDeposit(List<Deposit> deposits) {
        this.deposits = deposits;
    }

    @Override
    public void execute() {
        System.out.println("Завантаження депозитів з файлу...");

        String filePath = "C:\\pp\\deposits.txt";

        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");

                int depositID = Integer.parseInt(parts[0].trim());
                String depositType = parts[1].trim();
                String bankName = parts[2].trim();
                double interestRate = Double.parseDouble(parts[3].trim());
                int term = Integer.parseInt(parts[4].trim());
                int minimalSum = Integer.parseInt(parts[5].trim());

                Deposit deposit;

                switch (depositType) {
                    case "A":
                        deposit = new AccumulatingDeposit(depositID, bankName, interestRate, term, minimalSum);
                        break;
                    case "S":
                        deposit = new SavingDeposit(depositID, bankName, interestRate, term, minimalSum);
                        break;
                    case "U":
                        deposit = new UniversalDeposit(depositID, bankName, interestRate, term, minimalSum);
                        break;
                    default:
                        System.out.println("Невідомий тип депозиту: " + depositType);
                        continue;
                }

                deposits.add(deposit);
            }
            System.out.println("\nДепозити завантажено успішно!");
        } catch (FileNotFoundException e) {
            System.out.println("Файл не знайдено: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Сталася помилка: " + e.getMessage());
        }
    }
}
