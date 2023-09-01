package com.nhnacademy.mart;

import java.util.ArrayList;

public class Basket {
    private final ArrayList<Food> foods = new ArrayList<>();

    private int totalAmount = 0;

    public void add(Food food) {
        foods.add(food);
        totalAmount += food.getPrice();
    }

    public ArrayList<Food> getFoods() {
        return foods;
    }

    // getTotalAmount() -> 장바구니 최종 금액
    public int getTotalAmount() {
        return totalAmount;
    }
}
