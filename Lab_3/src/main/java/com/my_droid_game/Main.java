package com.my_droid_game;
import com.my_droid_game.Arena.BattleOneonOne;
import com.my_droid_game.Droids.Droid;
import com.my_droid_game.Droids.DroidDoctor;
import com.my_droid_game.Droids.DroidMag;
import com.my_droid_game.Arena.TeamBattle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;



public class Main {

    private static ArrayList<Droid> droids = new ArrayList<>();

    public static void readFile(String filePath) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Помилка при читанні файлу: " + e.getMessage());
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.out.println("Помилка при закритті файлу: " + e.getMessage());
            }
        }
    }

    public static void setOponents(){

        Scanner scanner = new Scanner(System.in);
        Droid oponent1 = null;
        Droid oponent2 = null;
        Main.ShowDroids();
        System.out.println("\nОбери номер першого суперника: ");

        int num1 = scanner.nextInt()-1;
        if(num1>=0 && num1 < droids.size()){
            oponent1 = droids.get(num1);
            System.out.println("Ви обрали: \n\n" + oponent1.toString());
            System.out.println("\nНатисніть Enter, щоб продовжити...");
            scanner.nextLine();
            scanner.nextLine();
        } else{
            System.out.println("Неправильний вибір, спробуйте ще раз.");
            return;
        }

        Main.ShowDroids();
        System.out.println("\nОбери номер другого суперника: ");

        int num2 = scanner.nextInt()-1;
        if(num2>=0 && num2 < droids.size()){
            oponent2 = droids.get(num2);
            System.out.println("Ви обрали: \n\n" + oponent2.toString());
            System.out.println("\nНатисніть Enter, щоб почати битву...");
            scanner.nextLine();
            scanner.nextLine();
        } else{
            System.out.println("Неправильний вибір, спробуйте ще раз.");
            return;
        }
        BattleOneonOne battle = new BattleOneonOne(oponent1, oponent2);
        battle.startBattle();
    }

    public static void setCommands() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nСкільки дроїдів буде в одній команді?: ");
        int n = scanner.nextInt();

        ArrayList<Droid> team1 = new ArrayList<>();
        ArrayList<Droid> team2 = new ArrayList<>();

        // Вибір дроїдів для команди 1
        System.out.println("\nОбираємо першу команду: ");
        Main.ShowDroids();
        for (int i = 0; i < n; i++) {
            System.out.println("\nОбери номер дроїда для команди 1 (додайте " + (i + 1) + "): ");

            int num = scanner.nextInt() - 1;
            if (num >= 0 && num < droids.size()) {
                team1.add(droids.get(num));
                System.out.println("Ви обрали: \n\n" + droids.get(num).toString());
            } else {
                System.out.println("Неправильний вибір, спробуйте ще раз.");
                i--; // Зменшуємо лічильник, щоб повторити вибір
            }
        }

        Main.ShowDroids();
        System.out.println("\nОбираємо другу команду: ");
        for (int i = 0; i < n; i++) {
            System.out.println("\nОбери номер дроїда для команди 2 (додайте " + (i + 1) + "): ");

            int num = scanner.nextInt() - 1;
            if (num >= 0 && num < droids.size()) {
                team2.add(droids.get(num));
                System.out.println("Ви обрали: \n\n" + droids.get(num).toString());
            } else {
                System.out.println("Неправильний вибір, спробуйте ще раз.");
                i--;
            }
        }

        TeamBattle battle = new TeamBattle(team1, team2);
        battle.startBattle();
    }

    public static void initializeDroids() {
        droids.add(new Droid("Флатершай", 100, 15));
        droids.add(new Droid("Пінкі Пай", 120, 10));
        droids.add(new DroidMag("Реріті", 80, 9, 50, 5));
        droids.add(new DroidDoctor("Еплджек", 80, 10, 10, 3));
        droids.add(new Droid("Стелла", 100, 15));
        droids.add(new Droid("Муза", 120, 10));
        droids.add(new DroidMag("Лейла", 80, 9, 50, 5));
        droids.add(new DroidDoctor("Блум", 80, 10, 10, 3));
    }

    public static Droid AddDroid(Scanner scanner){
        System.out.println("Якого дроїда ви хочете створити?");
        System.out.println("1. Звичайний \n2. Дроїд Лікар \n3. Дроїд Маг");

        int type = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Введіть імя: ");
        String name =scanner.nextLine();

        System.out.println("Введіть здоровя: ");
        int health = scanner.nextInt();

        System.out.println("Введіть кількість шкоди, який може завдати дроїд: ");
        int damage = scanner.nextInt();

        switch (type){
            case 1:
                return new Droid(name, health, damage);
            case 2:
                System.out.print("Введіть потужність лікування: ");
                int healingPower = scanner.nextInt();
                System.out.print("Введіть кількість лікувальних предметів: ");
                int healingItems = scanner.nextInt();
                return new DroidDoctor(name, health, damage, healingPower, healingItems);
            case 3:
                System.out.print("Введіть кількість мани: ");
                int mana = scanner.nextInt();
                System.out.print("Введіть потужність щита: ");
                int shieldPower = scanner.nextInt();
                return new DroidMag(name, health, damage, mana, shieldPower);
            default:
                System.out.println("Неправильний вибір, дроїд не створено.");
                return null;
        }
    }

    public static void ShowDroids(){
    for (int i = 0; i < droids.size(); i++) {
        System.out.print("________________________\n");
        System.out.println((i + 1) + ". " + droids.get(i).toString());
    }
}

    public static void main(String[] args) {
        int choice = 1;
        initializeDroids();
        Scanner scanner = new Scanner(System.in);
        System.out.println("\t\t---------------------------------------");

        System.out.print("\t\tВітаю, Ви граєте гру \"Битва Дроїдів\"!");
        System.out.println("\n\t\t--------------------------------------");


        do {
            System.out.println("\n***** Меню *****");
            System.out.println("1. Створити Дроїда"); //++++++
            System.out.println("2. Показати список дроїдів"); //+++++++
            System.out.println("3. Бій 1 на 1"); // +++++
            System.out.println("4  Бій комнадами");  //  +++++
            System.out.println("5. Відтворити бій з файлу"); //
            System.out.println("0. Завершити гру"); //+++++
            System.out.print("\nОберіть опцію: "); //++++++

            choice = scanner.nextInt();

            switch (choice){
                case 1:
                Droid newDroid = AddDroid(scanner);
                if(newDroid!=null) {
                    droids.add(newDroid);
                    System.out.println("Дроїд успішно створений! ");
                }
                    break;

                case 2:
                ShowDroids();
                    break;

                case 3:
                setOponents();
                    break;

                case 4:
                setCommands();
                    break;

                case 5:

                    System.out.print("\nЯкий бій ви хочете зчитати? \n1. Один на один \n2. Командний ");
                    int n = scanner.nextInt();
                    String FILE_PATH1 = "C:\\javalabs\\battleoneonone.txt";
                    String FILE_PATH2 = "C:\\javalabs\\team_battle.txt";
                    if (n == 1) {
                        System.out.println("Зчитування бою 1 на 1 з файлу...");

                        readFile(FILE_PATH1);
                    } else if (n == 2) {
                        System.out.println("Зчитування командного бою з файлу...");
                        readFile(FILE_PATH2);

                    } else {
                        System.out.println("Неправильний вибір.");
                    }

                    break;

                case 0:
                    System.out.print("Дякую за гру! Бувай!");
                    break;

                default:

                    System.out.print("Неправильний вибір, повтори ще раз");

            }

        } while (choice != 0);

        scanner.close();
    }
}
