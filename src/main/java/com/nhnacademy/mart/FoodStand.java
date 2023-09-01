package com.nhnacademy.mart;

import java.util.ArrayList;

public class FoodStand {

    private final ArrayList<Food> foods = new ArrayList<>();

    // TODO add 메서드 구현 (Complete)
    public void add(Food food) {
        foods.add(food);
    }
    // TODO 장바구니에 담은 Food 삭제 구현 (Not yet)
    public void remove() {
        foods.clear();
    }
}
