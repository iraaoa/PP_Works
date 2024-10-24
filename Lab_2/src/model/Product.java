package model;

import java.time.LocalDate;

public class Product {
    private int id;
    private String name;
    private String producer;
    private double price;
    private String termin; // роки місяці дні
    private int number;

    // Конструктор
    public Product(int id, String name, String producer, double price, String termin, int number) {
        this.id = id;
        this.name = name;
        this.producer = producer;
        this.price = price;
        this.termin = termin;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTermin() {
        return termin;
    }

    public void setTermin(String termin) {
        this.termin = termin;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


    public LocalDate calculateShelfLifeDate(LocalDate date) {
        String[] parts = termin.split(" ");
        int years = Integer.parseInt(parts[0]);
        int months = Integer.parseInt(parts[1]);
        int days = Integer.parseInt(parts[2]);
        return date.plusYears(years).plusMonths(months).plusDays(days);
    }


@Override
public String toString() {
    return name + "\n" +
            "id: " + id + "\n" +
            "Виробник: " + producer + "\n" +
            "Ціна: " + price + "\n" +
            "Термін зберігання: " + termin + "\n" +
            "Кількість: " + number + "\n_____________________________";
   }
}
