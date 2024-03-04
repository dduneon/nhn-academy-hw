package com.nhnacademy.shoppingmall.controller.cart;

import com.nhnacademy.shoppingmall.cart.repository.impl.CartRepositoryImpl;
import com.nhnacademy.shoppingmall.cart.service.CartService;
import com.nhnacademy.shoppingmall.cart.service.impl.CartServiceImpl;
import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping.Method;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.common.util.AlertUtils;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method = Method.POST, value = "/user/additem.do")
public class AddProductController implements BaseController {

  private final CartService cartService = new CartServiceImpl(new CartRepositoryImpl());

  /**
   * @param req
   * @param resp
   * @return
   */
  @Override
  public String execute(HttpServletRequest req, HttpServletResponse resp) {
    String productIdStr = req.getParameter("productId");
    if (Objects.isNull(productIdStr)) {
      return AlertUtils.alert(req, "잘못된 접근입니다.", "/index.do");
    }
    int productId = Integer.parseInt(productIdStr);
    HttpSession session = req.getSession(false);
    String userId = (String) session.getAttribute("USER_ID_SESSION");
    log.debug("productId: {}, userId: {}", productId, userId);

    if (cartService.isExistProductInCart(productId, userId)) {
      log.error("already exist");
      return AlertUtils.alert(req, "이미 장바구니에 해당 상품이 존재합니다!", "/index.do");
    }
    cartService.saveProductInCart(productId, userId);
    return "redirect:/user/cart.do";
  }
}
