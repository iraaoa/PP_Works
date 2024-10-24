package com.my_droid_game.Arena;

import com.my_droid_game.Droids.Droid;
import com.my_droid_game.Droids.DroidDoctor;
import com.my_droid_game.Droids.DroidMag;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

public class TeamBattle {


    private List<Droid> team1;
    private List<Droid> team2;
    private Random random;
    private FileWriter writer;


    private static final String FILE_PATH = "C:\\javalabs\\team_battle.txt";

    public TeamBattle(List<Droid> team1, List<Droid> team2) {
        this.team1 = team1;
        this.team2 = team2;
        this.random = new Random();


        for (Droid d : team1) {
            if (d instanceof DroidDoctor) {
                ((DroidDoctor) d).setBattle2(this);
            }
            if (d instanceof DroidMag) {
                ((DroidMag) d).setBattle2(this);
            }
        }

        for (Droid d : team2) {
            if (d instanceof DroidDoctor) {
                ((DroidDoctor) d).setBattle2(this);
            }
            if (d instanceof DroidMag) {
                ((DroidMag) d).setBattle2(this);
            }
        }


        try {
            writer = new FileWriter(FILE_PATH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    int round = 1;

    public void startBattle() {

        do {
            print("\n\n\n---------- ROUND " + round + " ----------\n");

            Droid attacker = team1.get(random.nextInt(team1.size()));
            Droid defender = team2.get(random.nextInt(team2.size()));

            if (attacker.IsAlive()) {
                Action(attacker, defender, random);

                print(attacker.getName() + " здоров'я: " + attacker.getHealth() + "\n");
                print(defender.getName() + " здоров'я: " + defender.getHealth() + "\n");

                if (!defender.IsAlive()) {
                    print(defender.getName() + " знищено!\n");
                    team2.remove(defender);
                }
            }

            if (team2.isEmpty()) {
                print("\n\n ***** Команда 1 виграла! *****\n");
                break;
            }

            attacker = team2.get(random.nextInt(team2.size()));
            defender = team1.get(random.nextInt(team1.size()));

            print("\n");
            if (attacker.IsAlive()) {
                Action(attacker, defender, random);
                print(attacker.getName() + " здоров'я: " + attacker.getHealth() + "\n");
                print(defender.getName() + " здоров'я: " + defender.getHealth() + "\n");

                if (!defender.IsAlive()) {
                    print(defender.getName() + " знищено!\n");
                    team1.remove(defender);
                }
            }

            if (team1.isEmpty()) {
                print("\n\n ***** Команда 2 виграла! *****\n");
                break;
            }

            round++;
        } while (!team1.isEmpty() && !team2.isEmpty());

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


        public void print (String str){
            System.out.print(str);
            try {
                writer.write(str);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

