package com.nhnacademy.mart;

public class Food {

    private final String name;
    private final int price;

    public Food(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object obj) {
        Food food;
        if (obj instanceof Food)
            food = (Food) obj;
        else return false;
        return (this.getName().equals(food.getName()));
    }
}
