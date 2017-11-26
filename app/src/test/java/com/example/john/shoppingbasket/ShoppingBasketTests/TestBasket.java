package com.example.john.shoppingbasket.ShoppingBasketTests;

import com.example.john.shoppingbasket.ShoppingBasketTools.Basket;
import com.example.john.shoppingbasket.ShoppingBasketTools.Item;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by John on 22/11/2017.
 */

public class TestBasket {

    Basket basket;
    Item item1;
    Item item2;

    @Before
    public void setUp() throws Exception {
        basket = new Basket(true);
        item1 = new Item("cd", "The Weeknd: Starboy", false, 10);
        item2 = new Item("cd", "Kanye West: College Dropout", true, 15);
    }

    @Test
    public void testCanAddItem() {
        basket.addItem(item1);
        assertEquals(1, basket.getNumberOfItems());
    }

    @Test
    public void canRemoveItem() {
        basket.addItem(item1);
        basket.removeItem(item1);
        assertEquals(0, basket.getNumberOfItems());
    }

    @Test
    public void removeItemNotPresent() {
        basket.addItem(item1);
        basket.removeItem(item2);
        assertEquals(1, basket.getNumberOfItems());
    }

    @Test
    public void canRemoveAllItems() {
        basket.addItem(item1);
        basket.addItem(item1);
        basket.addItem(item2);
        basket.emptyBasket();
        assertEquals(0, basket.getNumberOfItems());
    }

    @Test
    public void canGetTotalWithNoItems() {
        assertEquals(0, basket.getNumberOfItems());
    }

    @Test
    public void testCanGetTotalNoDiscount() {
        basket.addItem(item1);
        basket.addItem(item2);
        double actual = basket.getTotalPrice();
        assertEquals(25.00, actual, 0.02);
    }

    @Test
    public void checkCanGetBogofTotal() {
        basket.addItem(item2);
        basket.addItem(item2);
        double total = basket.getTotalPrice();
        double expected = basket.applyBogof(total);
        assertEquals(15.00, expected, 0.02);
    }

    @Test
    public void checkCanGetBidSpendDiscount() {
        basket.addItem(item1);
        basket.addItem(item2);
        double total = basket.getTotalPrice();
        double expected = basket.applyBigSpendDiscount(total);
        assertEquals(22.5, expected, 0.02);
    }

    @Test
    public void checkCanApplyLoyaltyBonus() {
        basket.addItem(item1);
        basket.addItem(item2);
        double total = basket.getTotalPrice();
        double expected = basket.applyLoyaltyBonus(total);
        assertEquals(24.5, expected, 0.02);
    }

    @Test
    public void checkCanGetCompleteTotal() {
        basket.addItem(item1);
        basket.addItem(item1);
        basket.addItem(item2);
        basket.addItem(item2);
        double expected = basket.getFinalTotal();
        assertEquals(30.87, expected, 0.02);
    }

}
