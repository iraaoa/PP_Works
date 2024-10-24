package com.my_droid_game.Droids;
import com.my_droid_game.Arena.BattleOneonOne;
import com.my_droid_game.Arena.TeamBattle;

public class DroidMag extends Droid {
    private int mana;
    private int shieldPower;
    private BattleOneonOne battle;
    private TeamBattle battle2;

    public DroidMag(String name, int health, int damage, int mana, int shieldPower) {
        super(name, health, damage);
        this.mana = mana;
        this.shieldPower = shieldPower;

    }
    public void setBattle(BattleOneonOne battle) {
        this.battle = battle;
    }

    public void setBattle2(TeamBattle battle2) {
        this.battle2 = battle2;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getShieldPower() {
        return shieldPower;
    }

    public void setShieldPower(int shieldPower) {
        this.shieldPower = shieldPower;
    }




    @Override
    public void takeDamage(int damage) {
        if (getHealth() < 20 && mana >= 10) {

            printMessage(this.getName() + " використовує магічний щит, блокуючи " + shieldPower + " очків шкоди.\n");
            mana -= 10;
            int effectiveDamage = damage - shieldPower;

            if (effectiveDamage < 0) {
                effectiveDamage = 0;
            }
            setHealth(Math.max(0, getHealth() - effectiveDamage));
        } else {
            if (mana < 10) {
                printMessage(this.getName() + " не вистачає мани для використання магічного щита.\n");
            }
            super.takeDamage(damage);
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
                "\nМана: " + mana +
                "\nПотужність щита: " + shieldPower;
    }

}



