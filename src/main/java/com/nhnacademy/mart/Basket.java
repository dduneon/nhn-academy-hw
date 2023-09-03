package com.nhnacademy.mart;

import java.util.ArrayList;

public class Basket {
    private final ArrayList<Food> foods = new ArrayList<>();

    private int totalAmount = 0;

    public void add(Food food) {
        foods.add(food);
        Logback.logger.info("Basket.add() : {}({}) 상품이 추가됨", food.getName(), food.getPrice());
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
