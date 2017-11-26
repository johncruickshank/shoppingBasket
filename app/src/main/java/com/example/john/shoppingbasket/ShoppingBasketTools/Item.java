package com.example.john.shoppingbasket.ShoppingBasketTools;

/**
 * Created by John on 24/11/2017.
 */

public class Item {
    private String name;
    private String description;
    private boolean bogof;
    private double price;


    public Item(String name, String description, boolean bogof, double price) {
        this.name = name;
        this.description = description;
        this.bogof = bogof;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public boolean isBogof() {
        return bogof;
    }

    public double getPrice() {
        return price;
    }
}
