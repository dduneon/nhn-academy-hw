package com.nhnacademy.shoppingmall.controller.order;

import com.nhnacademy.shoppingmall.cart.repository.impl.CartRepositoryImpl;
import com.nhnacademy.shoppingmall.cart.service.CartService;
import com.nhnacademy.shoppingmall.cart.service.impl.CartServiceImpl;
import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping.Method;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.common.util.AlertUtils;
import com.nhnacademy.shoppingmall.join.domain.ProductInfoInCart;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method = Method.POST, value = "/user/order.do")
public class OrderController implements BaseController {

  private final CartService cartService = new CartServiceImpl(new CartRepositoryImpl());

  /**
   * @param req
   * @param resp
   * @return
   */
  @Override
  public String execute(HttpServletRequest req, HttpServletResponse resp) {
    HttpSession session = req.getSession(false);
    String userId = (String) session.getAttribute("USER_ID_SESSION");

    List<ProductInfoInCart> orderList = cartService.getUserCartList(userId);
    if (orderList.isEmpty()) {
      return AlertUtils.alert(req, "장바구니가 비어있습니다. 상품을 담고 시도해 주세요", "/user/cart.do");
    }
    int totalPrice = cartService.getTotalPriceInCart(userId);
    //todo 유저 address req로 보내주기
    req.setAttribute("ORDER_LIST", orderList);
    req.setAttribute("TOTAL_PRICE", totalPrice);
    return "shop/order/order";
  }
}
