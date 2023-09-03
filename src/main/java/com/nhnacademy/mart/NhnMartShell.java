package com.nhnacademy.mart;

import java.util.Scanner;
import java.util.StringTokenizer;
import org.slf4j.LoggerFactory;

/**
 * NhnMartShell
 *
 * 사용자와의 커뮤니케이션을 담당
 */
public class NhnMartShell {

    public static void main(String[] args) {
        NhnMart mart = new NhnMart();
        mart.prepareMart();
        BuyList buyList;
        try {
            buyList = inputBuyListFromShell();
            Customer junHyeon = new Customer(buyList);

            // 장바구니를 챙긴다.
            junHyeon.bring(mart.provideBasket());

            // 식품을 담는다.
            junHyeon.pickFoods(mart.getFoodStand());

            // 카운터에서 계산한다.
            junHyeon.payTox(mart.getCounter());
        } catch (IllegalArgumentException e) {
            System.out.println("잘못된 값을 입력하였습니다. 다시 확인해주세요!");
            Logback.logger.error("잘못된 값을 입력하셨습니다. 다시 확인해주세요!");
        }
    }

    /**
     * 사용자 입력을 바탕으로 BuyList(구매 목록) 를 만들어서 반환하는 함수
     * @return BuyList 반환
     */
    private static BuyList inputBuyListFromShell() {
        BuyList buyList = new BuyList();
        Scanner sc = new Scanner(System.in);

        System.out.println("NHN 마트에 오신것을 환영합니다! 사고 싶은 물건을 골라주세요\n");
        System.out.print("> ");
        String str = sc.nextLine();
        StringTokenizer st = new StringTokenizer(str);

        while(st.hasMoreTokens()) {
            String itemName = st.nextToken();
            int itemAmount;
            // 이름, 수량 한 쌍으로 입력되어야 하지만, 그렇지 않은 경우 예외 처리
            if (!st.hasMoreTokens())    throw new IllegalArgumentException("잘못된 값을 입력하셨습니다.");
            try {
                itemAmount = Integer.parseInt(st.nextToken());
                buyList.add(new BuyList.Item(itemName, itemAmount));
                // 숫자가 아닌 다른 문자를 입력한 경우 발생
            } catch (NumberFormatException e) {
                System.out.println("잘못된 값을 입력하였습니다. 수량에 숫자가 아닌 다른 문자를 입력하였는지 확인해주세요.");
                Logback.logger.error("NhnMartShell.inputBuyListFromShell() : NumberFormatException 발생, 숫자 입력 오류");
            }
        }
        return buyList;
    }
}
