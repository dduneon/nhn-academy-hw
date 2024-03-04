package com.nhnacademy.mart;

import java.util.ArrayList;

public class FoodStand {

    private final ArrayList<Food> foods = new ArrayList<>();

    public void add(Food food) {
        foods.add(food);
    }
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
