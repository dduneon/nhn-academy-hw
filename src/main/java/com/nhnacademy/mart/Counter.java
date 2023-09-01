package com.nhnacademy.mart;

public class Counter {

    // TODO pay 메서드 구현 (카운터에서 계산 처리 메서드)
    public void pay(int total, Customer customer) {
        int rest = customer.getMoney() - total;
        if (rest < 0)
            throw new IllegalArgumentException("잔액이 부족합니다.");
        System.out.println("총 가격은 " + customer.getMoney() + "입니다.");
        System.out.println("고객님 결제 후 잔액 : " + rest);
        customer.setMoney(rest);
    }
}
