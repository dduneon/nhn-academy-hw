package com.nhnacademy.mart;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CounterTest {

    @Test
    void noMoneyTest() {
        Customer customer = new Customer(new BuyList());
        int total = 25000;
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Counter().pay(total, customer));
    }

    @Test
    void yesMoneyTest() {
        Customer customer = new Customer(new BuyList());
        int total = 15000;
        Assertions.assertDoesNotThrow(() -> new Counter().pay(total, customer));
    }
}