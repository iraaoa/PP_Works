package com.my_droid_game.Droids;
import java.util.Random;

public class Droid {
    private String name;
    private int health;
    private int damage;


    public Droid(String name, int health, int damage){
        this.name = name;
        this.health = health;
        this.damage = damage;
    }

    public String getName(){
        return name;
    }

    public void setName (String name){
        this.name = name;
    }

    public int getHealth (){
        return health;
    }



    public void setHealth (int health){
        this.health = health;
    }

    public int getDamage (){
        return damage;
    }

    public void setDamage (int damage){
        this.damage=damage;
    }


    public boolean IsAlive() {
        return this.health > 0;
    }


    public void takeDamage(int damage) {
        health = Math.max(0, health - damage);
    }



    @Override
    public String toString() {
        return
                name  +
                        "\nЗдоров'я: " + health +
                        "\nУрон: " + damage;
    }

}


