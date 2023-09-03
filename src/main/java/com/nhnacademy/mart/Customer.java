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

    /**
     * 구매목록에 있는 제품들을 장바구니로 옮기는 메서드
     * 존재하지 않는 상품명이라면 에러를 발생시키고 종료
     * 상품 수량이 부족한 경우 에러를 발생시키고 종료
     * @param foodStand
     */
    public void pickFoods(FoodStand foodStand) {
        for (BuyList.Item item: buyList.getItems()) {
            Food tmp;
            // getName()의 return이 null이면 해당 상품이 존재하지 않는 것임
            if ((tmp = foodStand.findFood(item.getName())) == null) {
                Logback.logger.error("Customer.pickfood() : 존재하지 않는 상품명, {} 입력", item.getName());
                throw new IllegalArgumentException("존재하지 않는 상품명을 입력하셨습니다");
            }
            for (int i=0; i<item.getAmount(); i++) {
                if(foodStand.remove(tmp)) {
                    basket.add(tmp);
                } else {
                    Logback.logger.error("Customer.pickFood() : {} 상품 수량이 더이상 존재하지 않습니다", tmp.getName());
                    System.out.println(tmp.getName() + " 상품 수량이 존재하지 않습니다. 확인 해주세요!");
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
