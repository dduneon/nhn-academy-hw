package com.nhnacademy.mart;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomerTest {

    Customer customer;
    BuyList buyList;    Basket basket;
    FoodStand foodStand;

    @BeforeEach
    void init() {
        buyList = new BuyList();
        customer = new Customer(buyList);
        basket = new Basket();
        foodStand = new FoodStand();

        customer.bring(basket);

        foodStand.add(new Food("Test1", 1000));
        foodStand.add(new Food("Test2", 2000));
    }

    @Test
    void pickFoods() {
        buyList.add(new BuyList.Item("Test1", 1));
        customer.pickFoods(foodStand);
        Assertions.assertEquals(1000, basket.getTotalAmount());
    }


    @Test
    void pickNoFoods(){
        buyList.add(new BuyList.Item("Test1", 2));
        Assertions.assertThrows(IllegalArgumentException.class, () -> customer.pickFoods(foodStand));
    }
    @Test
    void pickNotFoundFood(){
        buyList.add(new BuyList.Item("NotFound",1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> customer.pickFoods(foodStand));
    }
    @Test
    void YespayTox() {
        buyList.add(new BuyList.Item("Test1", 1));
        customer.pickFoods(foodStand);
        customer.payTox(new Counter());

        Assertions.assertEquals(19000, customer.getMoney());
    }
}