package com.nhnacademy.mart;

import java.util.ArrayList;

public class Basket {
    private final ArrayList<Food> foods = new ArrayList<>();

    public void add(Food food) {
        foods.add(food);
    }

    public ArrayList<Food> getFoods() {
        return foods;
    }

    // getTotalAmount() -> 장바구니 최종 금액
    public int getTotalAmount() {
        int total = 0;
        for (Food food: foods) {
            total += food.getPrice();
        }
        return total;
    }
}
