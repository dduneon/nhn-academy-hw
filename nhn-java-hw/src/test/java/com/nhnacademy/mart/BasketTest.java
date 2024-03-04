package com.nhnacademy.mart;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BasketTest {

    @Test
    void addFoodTest() {
        Food test = new Food("test", 1000);
        Basket basket = new Basket();
        basket.add(test);

        Assertions.assertEquals(test, basket.getFoods().get(0));
    }

    @Test
    void getFoodTest() {
        Basket basket = new Basket();
        Food[] foodList = {new Food("김치", 1000), new Food("고기", 2000), new Food("쌈장", 500)};
        for (Food food: foodList) {
            basket.add(food);
        }

        ArrayList<Food> getFoodList = basket.getFoods();
        for (int i=0; i<getFoodList.size(); i++) {
            Assertions.assertEquals(foodList[i], getFoodList.get(i));
        }
    }

    @Test
    void getTotalAmount() {
        Basket basket = new Basket();
        Food[] foodList = {new Food("김치", 1000), new Food("고기", 2000), new Food("쌈장", 500)};
        for (Food food: foodList) {
            basket.add(food);
        }
        Assertions.assertEquals(3500, basket.getTotalAmount());
    }
}