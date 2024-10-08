package com.booleanuk.core.basket;

public class BasketItemFormatted {

    private String name;
    private int amount;
    private double price;
    private double discount;

    public BasketItemFormatted(String name, int amount, double price, double discount) {
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
