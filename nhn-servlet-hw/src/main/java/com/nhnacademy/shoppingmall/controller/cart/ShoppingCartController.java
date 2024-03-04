package com.nhnacademy.shoppingmall.controller.cart;

import com.nhnacademy.shoppingmall.cart.repository.impl.CartRepositoryImpl;
import com.nhnacademy.shoppingmall.cart.service.CartService;
import com.nhnacademy.shoppingmall.cart.service.impl.CartServiceImpl;
import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping.Method;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.common.util.AlertUtils;
import com.nhnacademy.shoppingmall.join.domain.ProductInfoInCart;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method = Method.GET, value = "/user/cart.do")
public class ShoppingCartController implements BaseController {

  private final CartService cartService = new CartServiceImpl(new CartRepositoryImpl());

  /**
   * @param req
   * @param resp
   * @return
   */
  @Override
  public String execute(HttpServletRequest req, HttpServletResponse resp) {
    HttpSession session = req.getSession(false);
    if (Objects.isNull(session)) {
      return AlertUtils.alert(req, "로그인이 필요한 서비스입니다. 로그인해 주세요", "/login.do");
    }
    String userId = (String) session.getAttribute("USER_ID_SESSION");
    if (Objects.isNull(userId) || userId.isEmpty()) {
      session.invalidate();
      return AlertUtils.alert(req, "정보를 불러오는 데에 실패하였습니다. 다시 로그인해 주세요", "/login.do");
    }

    List<ProductInfoInCart> productInfoInCarts = cartService.getUserCartList(userId);
    int totalPrice = cartService.getTotalPriceInCart(userId);
    if (Objects.nonNull(productInfoInCarts)) {
      req.setAttribute("CART_PRODUCTS", productInfoInCarts);
      req.setAttribute("TOTAL_PRICE", totalPrice);
    } else {
      return AlertUtils.alert(req, "정보를 불러오는 데에 실패하였습니다.", "/index.do");
    }
    return "shop/cart/cart";
  }
}
