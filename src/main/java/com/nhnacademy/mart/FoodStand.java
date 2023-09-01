package com.nhnacademy.mart;

import java.util.ArrayList;

public class FoodStand {

    private final ArrayList<Food> foods = new ArrayList<>();

    // TODO add 메서드 구현 (Complete)
    public void add(Food food) {
        foods.add(food);
    }
    // TODO 장바구니에 담은 Food 삭제 구현 (Complete)
    public void remove(Basket basket) {
        for(Food food: basket.getFoods()) {
            if(!this.foods.remove(food)) {
                throw new IllegalArgumentException("매대에 없는 상품을 구매하셨습니다");
            }
        }
    }

}
