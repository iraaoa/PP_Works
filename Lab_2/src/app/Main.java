package app;

import model.Product;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static Product readProduct(Scanner scanner) {
        System.out.println("Введіть дані для продукту:");

        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Назва: ");
        String name = scanner.nextLine();

        System.out.print("Виробник: ");
        String producer = scanner.nextLine();

        System.out.print("Ціна: ");
        double price = Double.parseDouble(scanner.nextLine());

        System.out.print("Термін зберігання (роки місяці дні): ");
        String termin = scanner.nextLine();

        System.out.print("Кількість: ");
        int number = scanner.nextInt();

        return new Product(id, name, producer, price, termin, number);
    }

    public static void findProductByName(ArrayList<Product> products, Scanner scanner) {
        if (products.isEmpty()) {
            System.out.println("Список продуктів порожній, будь ласка, введіть їх спочатку.");
            return;
        }

        System.out.println("Введіть назву продукту, який хочете знайти:");
        String name = scanner.nextLine();

        System.out.println("Список знайдених продуктів:");
        boolean found = false;
        for (Product pr : products) {
            if (pr.getName().equalsIgnoreCase(name)) {
                found = true;
                System.out.println(pr);
            }
        }

        if (!found) {
            System.out.println("Продукт із назвою \"" + name + "\" не знайдено.");
        }
    }

    public static void findByNameAndPrice(ArrayList<Product> products, Scanner scanner) {
        if (products.isEmpty()) {
            System.out.println("Список продуктів порожній, будь ласка, введіть їх спочатку.");
            return;
        }

        System.out.println("Введіть назву продукту, який хочете знайти:");
        String name = scanner.nextLine();

        System.out.println("Введіть максимальну ціну для цього продукту:");
        double price = Double.parseDouble(scanner.nextLine());

        boolean found = false;
        for (Product pr : products) {
            if (pr.getName().equalsIgnoreCase(name) && pr.getPrice() <= price) {
                found = true;
                System.out.println(pr);
            }
        }

        if (!found) {
            System.out.println("Продукт не знайдено.");
        }
    }

    public static void findProductsByTermin(ArrayList<Product> products, Scanner scanner) {
        if (products.isEmpty()) {
            System.out.println("Список продуктів порожній, будь ласка, введіть їх спочатку.");
            return;
        }

        System.out.println("Введіть термін зберігання у форматі 'роки місяці дні', який ви хочете перевірити:");
        String[] terminInput = scanner.nextLine().split(" ");
        int years = Integer.parseInt(terminInput[0]);
        int months = Integer.parseInt(terminInput[1]);
        int days = Integer.parseInt(terminInput[2]);
        LocalDate terminLife = LocalDate.now().plusYears(years).plusMonths(months).plusDays(days);

        boolean found = false;
        for (Product pr : products) {
            LocalDate lifeDate = pr.calculateShelfLifeDate(LocalDate.now());
            if (lifeDate.isAfter(terminLife)) {
                found = true;
                System.out.println(pr);
            }
        }

        if (!found) {
            System.out.println("Продуктів з терміном зберігання більшим за заданий не знайдено.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Product> products = new ArrayList<>();

        boolean menu = true;

        while (menu) {
            System.out.println("Оберіть пункт нижче:");
            System.out.println("1. Ввести продукти.");
            System.out.println("2. Надрукувати усі дані про продукти.");
            System.out.println("3. Знайти продукти по назві.");
            System.out.println("4. Знайти продукти по назві та які коштують менше заданої ціни.");
            System.out.println("5. Знайти продукти за терміном зберігання.");
            System.out.println("6. Завершити програму.");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Вкажіть кількість продуктів: ");
                    int count = scanner.nextInt();
                    scanner.nextLine();

                    for (int k = 0; k < count; k++) {
                        Product prod = readProduct(scanner);
                        products.add(prod);
                    }
                    break;

                case 2:
                    if (products.isEmpty()) {
                        System.out.println("Список продуктів порожній, будь ласка, введіть їх спочатку.");
                    } else {
                        System.out.println("***** Список продуктів *****");
                        System.out.println("_____________________________");

                        int index = 1;
                        for (Product pr : products) {
                            System.out.println("Продукт " + index + ":");
                            System.out.println(pr);
                            index++;
                        }
                    }
                    break;

                case 3:
                    findProductByName(products, scanner);
                    break;

                case 4:
                    findByNameAndPrice(products, scanner);
                    break;

                case 5:
                    findProductsByTermin(products, scanner);
                    break;

                case 6:
                    menu = false;
                    System.out.println("Програму завершено, до побачення.");
                    break;

                default:
                    System.out.println("Неправильний вибір, спробуйте ще раз.");
            }
        }
    }
}
