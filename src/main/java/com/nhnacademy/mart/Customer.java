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
        this.money = 20000;
    }

    // 장바구니 챙기기
    public void bring(Basket basket) {
        this.basket = basket;
    }

    // TODO pickFoods 메서드 구현 (Complete)
    // TODO add 메서드 추가 및 remove 옮기기 구현하기
    public void pickFoods(FoodStand foodStand) {
        for (BuyList.Item item: buyList.getItems()) {
            Food tmp;
            if ((tmp = foodStand.findFood(item.getName())) == null) {
                Logback.logger.error("Customer.pickfood() : 존재하지 않는 상품명, {} 입력", item.getName());
                throw new IllegalArgumentException("존재하지 않는 상품명을 입력하셨습니다");
            }
            for (int i=0; i<item.getAmount(); i++) {
                if(foodStand.remove(tmp)) {
                    basket.add(tmp);
                } else {
                    Logback.logger.error("Customer.pickFood() : {} 상품이 존재하지 않습니다", tmp.getName());
                    System.out.println(tmp.getName() + " 상품이 존재하지 않습니다. 확인 해주세요!");
                }
            }
        }
    }



    // TODO payTox 메서드 구현
    public void payTox(Counter counter) {
        try {
            counter.pay(this.basket.getTotalAmount(), this);
        } catch (IllegalArgumentException e) {
            System.out.println("잔액이 부족합니다! 계산할 총 금액 : " + basket.getTotalAmount() + ", 잔액 : " + getMoney());
        }
    }

    // Money getter() / setter()
    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
