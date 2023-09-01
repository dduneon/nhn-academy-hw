package com.nhnacademy.mart;

public class NhnMart {

    private final FoodStand foodStand = new FoodStand();
    private final Counter counter = new Counter();

    public void prepareMart() {
        fillFoodStand();
    }

    // 음식 세팅
    private void fillFoodStand() {
        for (int i = 0; i < 2; i++) {
            foodStand.add(new Food("양파", 1000));
        }
        for (int i = 0; i < 5; i++) {
            foodStand.add(new Food("계란", 5000));
        }
        for (int i = 0; i < 10; i++) {
            foodStand.add(new Food("파", 500));
        }
        for (int i = 0; i < 20; i++) {
            foodStand.add(new Food("사과", 2000));
        }
    }

    public Basket provideBasket() {
        return new Basket();
    }

    // getFoodStand() 구현
    public FoodStand getFoodStand() {
        return foodStand;
    }

    // getCounter() 구현
    public Counter getCounter() {
        return counter;
    }
}
