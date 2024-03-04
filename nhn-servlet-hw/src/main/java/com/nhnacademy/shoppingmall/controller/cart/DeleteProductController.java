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
@RequestMapping(method = Method.POST, value = "/user/deleteitem.do")
public class DeleteProductController implements BaseController {

  private final CartService cartService = new CartServiceImpl(new CartRepositoryImpl());

  /**
   * @param req
   * @param resp
   * @return
   */
  @Override
  public String execute(HttpServletRequest req, HttpServletResponse resp) {
    String deleteProductIdStr = req.getParameter("item");
    if (Objects.isNull(deleteProductIdStr)) {
      return AlertUtils.alert(req, "잘못된 접근입니다.", "/user/cart.do");
    }
    int deleteProductId = Integer.parseInt(deleteProductIdStr);
    HttpSession session = req.getSession(false);
    String userId = (String) session.getAttribute("USER_ID_SESSION");
    if (Objects.isNull(userId) || userId.isEmpty()) {
      return AlertUtils.alert(req, "오류가 발생하였습니다. 다시 시도해 주세요.", "/user/cart.do");
    }
    log.debug("productID: {}", deleteProductId);
    if (cartService.isExistProductInCart(deleteProductId, userId)) {
      cartService.deleteProductInCart(deleteProductId, userId);
    } else {
      return AlertUtils.alert(req, "장바구니에 존재하지 않는 상품입니다.", "/user/cart.do");
    }
    return "redirect:/user/cart.do";
  }
}
