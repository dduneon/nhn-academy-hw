package com.nhnacademy.mart;

public class Customer {

    // 고객 구매 목록
    private final BuyList buyList;

    // 고객 장바구니
    private Basket basket;

    // 고객 잔돈
    private int money;

    public Customer(BuyList buyList) {
        this.buyList = buyList;
        setMoney(20000);
    }

    // 장바구니 챙기기
    public void bring(Basket basket) {
        this.basket = basket;
    }

    // TODO pickFoods 메서드 구현 (Complete)
    // TODO add 메서드 추가 및 remove 옮기기 구현하기
    public void pickFoods(FoodStand foodStand) {
        foodStand.remove(basket);
        buyList.add();
    }



    // TODO payTox 메서드 구현
    public void payTox(Counter counter) {
        counter.pay(this.basket.getTotalAmount(), this);
    }

    // Money getter() / setter()
    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
