package com.my_droid_game.Arena;

import com.my_droid_game.Droids.Droid;
import com.my_droid_game.Droids.DroidDoctor;
import com.my_droid_game.Droids.DroidMag;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Random;

public class BattleOneonOne {

    private Droid op1;
    private Droid op2;
    private FileWriter writer;

    private static final String FILE_PATH = "C:\\javalabs\\battleoneonone.txt";

    public BattleOneonOne(Droid op1, Droid op2) {
        this.op1 = op1;
        this.op2 = op2;


        if (op1 instanceof DroidDoctor) {
            ((DroidDoctor) op1).setBattle(this);
        }
        if (op2 instanceof DroidDoctor) {
            ((DroidDoctor) op2).setBattle(this);
        }

        if (op1 instanceof DroidMag) {
            ((DroidMag) op1).setBattle(this);
        }
        if (op2 instanceof DroidMag) {
            ((DroidMag) op2).setBattle(this);
        }


        try {
            writer = new FileWriter(FILE_PATH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }




    public void startBattle() {
        Random random = new Random();
        int round = 1;

        print("\n************************************************\n");
        print("Битва між " + op1.getName() + " та " + op2.getName() + "\n");
        print("************************************************\n");

        Droid attacker, defender;
        if (random.nextBoolean()) {
            attacker = op1;
            defender = op2;
        } else {
            attacker = op2;
            defender = op1;
        }
        print(attacker.getName() + " атакує першим!\n");

        do {
            print("\n\n\n---------- ROUND " + round + " ----------\n");

            Action(attacker, defender, random);
            print("\n");
            print(op1.getName() + " здоров'я: " + op1.getHealth() + "\n");
            print(op2.getName() + " здоров'я: " + op2.getHealth() + "\n");

            Droid temp = attacker;
            attacker = defender;
            defender = temp;

            round++;
        } while (defender.IsAlive() && attacker.IsAlive());

        if (attacker.IsAlive()) {
            print("\n***** " + attacker.getName() +   " перемогла у битві!  *****\n");
        } else {
            print("\n***** " + defender.getName() +   " перемогла у битві!  *****\n");
        }


        try {
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void Action(Droid attacker, Droid defender, Random random) {
        if (attacker instanceof DroidDoctor) {
            DroidDoctor doctor = (DroidDoctor) attacker;

            if (doctor.getHealth() < 20) {
                doctor.heal();
            }

                int damage = random.nextInt(attacker.getDamage()) + 1;
            print(attacker.getName() + " атакує " + defender.getName() + " і завдає " + damage + " шкоди.\n");

            defender.takeDamage(damage);

        } else if (attacker instanceof DroidMag) {
            int damage = random.nextInt(attacker.getDamage()) + 1;
            print(attacker.getName() + " кидає магічне закляття на " + defender.getName() + " і завдає " + damage + " шкоди.\n");
            defender.takeDamage(damage);

        } else if(attacker instanceof Droid) {

                int damage = random.nextInt(attacker.getDamage()) + 1;
            print(attacker.getName() + " атакує " + defender.getName() + " і завдає " + damage + " шкоди.\n");
            defender.takeDamage(damage);
        }

    }



    public void print(String str) {
        System.out.print(str);
        try {
             writer.write(str);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
