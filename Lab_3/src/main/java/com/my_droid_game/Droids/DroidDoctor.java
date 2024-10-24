package com.my_droid_game.Droids;
import com.my_droid_game.Arena.BattleOneonOne;
import com.my_droid_game.Arena.TeamBattle;
import java.util.Random;

public class DroidDoctor extends Droid {
    private int healingPower;
    private int healingItems;
    private Random random;
    private BattleOneonOne battle;
    private TeamBattle battle2;

    public DroidDoctor(String name, int health, int damage, int healingPower, int healingItems) {
        super(name, health, damage);
        this.healingPower = healingPower;
        this.healingItems = healingItems;
        this.random = new Random();
    }

    public int getHealingItems() {
        return healingItems;
    }

    public void setHealingPower(int healingPower) {
        this.healingPower = healingPower;
    }

    public void setHealingItems(int healingItems) {
        this.healingItems = healingItems;
    }

    public void setBattle(BattleOneonOne battle) {
        this.battle = battle;
    }

    public void setBattle2(TeamBattle battle2) {
        this.battle2 = battle2;
    }

    public int getHealingPower() {
        return healingPower;
    }



    public void heal() {
        if (healingItems > 0) {
            int healthRestored = random.nextInt(healingPower) + 1;
            setHealth(getHealth() + healthRestored);
            healingItems--;
            printMessage(getName() + " відновлює " + healthRestored + " очків здоров'я.\n");
        } else {
            printMessage(getName() + " не вистачає лікувальних предметів щоб вилікувати себе!\n");
        }
    }


    private void printMessage(String message) {
        if (battle != null) {
            battle.print(message);
        } else if (battle2 != null) {
            battle2.print(message);
        } else {
            System.out.println(message);
        }
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nЛікувальна потужність: " + healingPower +
                "\nКількість спроб лікування: " + healingItems;
    }
}




