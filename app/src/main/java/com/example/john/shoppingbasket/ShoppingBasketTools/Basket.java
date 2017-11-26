package com.example.john.shoppingbasket.ShoppingBasketTools;

import java.util.ArrayList;

/**
 * Created by John on 24/11/2017.
 */

public class Basket {

    private ArrayList<Item> items;
    private boolean loyalCustomer;
    private double discountRatio;
    private double bigSpendAmount;
    private double loyalCustomerRatio;

    public Basket(boolean loyalCustomer) {
        this.items = new ArrayList<>();
        this.loyalCustomer = loyalCustomer;
        this.discountRatio = 0.9;
        this.bigSpendAmount = 20;
        this.loyalCustomerRatio = 0.98;
    }

    private boolean isLoyalCustomer() {
        return loyalCustomer;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    // remove a given item and break from loop so only one is removed
    public void removeItem(Item itemToBeRemoved) {
        for (int i = 0; i < items.size(); i++)
            if (itemToBeRemoved.equals(items.get(i))) {
                items.remove(i);
                break;
            }
    }

    // return number of items in basket
    public int getNumberOfItems() {
        return items.size();
    }

    // get total then remove discounts in order
    public double getTotalPrice() {
        double total = 0;
        for (Item item : items) {
            total += item.getPrice();
        }
        return total;
    }

    // check for buy one get one free and apply discount if necessary
    public double applyBogof(double total) {
        double bogofDiscount = checkBogof();
        return total - bogofDiscount;
    }

    // apply discount if minimum spend is met
    public double applyBigSpendDiscount(double total) {
        if (total > bigSpendAmount) {
            return total * discountRatio;
        }
        return total;
    }

    // check if customer using basket is a loyal customer and add discount if so
    public double applyLoyaltyBonus(double total) {
        if (isLoyalCustomer()) {
            return total * loyalCustomerRatio;
        }
        return total;
    }

    // get final total minus any discounts
    public double getFinalTotal() {
        double total = getTotalPrice();
        double bogofTotal = applyBogof(total);
        double bigSpendDiscount = applyBigSpendDiscount(bogofTotal);
        return applyLoyaltyBonus(bigSpendDiscount);
    }

    public void emptyBasket() {
        items.clear();
    }

    // check if item is on offer and return amount to be discounted
    private double checkBogof() {
        double discount = 0;
        // loop through unique items in basket
        for (Item uniqueItem : getUniqueItems()) {
            // check if item has discount and if there are more than one of the item to get one free
            if (uniqueItem.isBogof() && (getAmountOfItem(uniqueItem) > 1)) {
                discount += ((getAmountOfItem(uniqueItem) / 2) * uniqueItem.getPrice());
            }
        }
        return discount;
    }

    // for a given item, return the number of them in the basket
    private int getAmountOfItem(Item item) {
        int count = 0;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).equals(item)) {
                count = count +1;
            }
        }
        return count;
    }

    // return one of every item in basket to discount duplicates when applying bogof discount
    private ArrayList<Item> getUniqueItems() {
        ArrayList<Item> uniqueItems = new ArrayList<>();
        for (Item item : items) {
            if (!uniqueItems.contains(item)) {
                uniqueItems.add(item);
            }
        }
        return uniqueItems;
    }

}
