package com.nhnacademy.mart;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BuyListTest {
    @Test
    void buyListitemsTest() {
        ArrayList<BuyList.Item> items = new ArrayList<>();
        items.add(new BuyList.Item("Test1", 1));
        items.add(new BuyList.Item("Test2", 2));
        items.add(new BuyList.Item("Test3", 3));
        items.add(new BuyList.Item("Test4", 4));

        BuyList buyList = new BuyList();
        buyList.add(new BuyList.Item("Test1", 1));
        buyList.add(new BuyList.Item("Test2", 2));
        buyList.add(new BuyList.Item("Test3", 3));
        buyList.add(new BuyList.Item("Test4", 4));

        for(int i=0; i<4; i++) {
            Assertions.assertEquals(items.get(i).getName(), buyList.getItems().get(i).getName());
        }
    }
}