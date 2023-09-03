package com.nhnacademy.mart;

import java.util.ArrayList;

public class FoodStand {

    private final ArrayList<Food> foods = new ArrayList<>();

    // TODO add 메서드 구현 (Complete)
    public void add(Food food) {
        foods.add(food);
    }
    // TODO 장바구니에 담은 Food 삭제 구현 (Complete)
    public boolean remove(Food food) {
        return foods.remove(food);
    }

    public Food findFood(String name) {
        for(Food food: foods) {
            if (food.getName().equals(name))
                return food;
        }
        return null;
    }

}
