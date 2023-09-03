package com.nhnacademy.mart;

import java.util.ArrayList;

public class BuyList {

    private final ArrayList<Item> items = new ArrayList<>();

    // TODO add 메서드 생성 (Complete)
    public void add(Item item) {
        Logback.logger.info("BuyList.add() : {} {}개가 추가되었습니다.", item.name, item.amount);
        items.add(item);
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public static class Item {
        private final String name;
        private final int amount;
        public Item(String name, int amount) {
            this.name = name;
            this.amount = amount;
        }

        public String getName() {
            return name;
        }

        public int getAmount() {
            return amount;
        }
    }
}
