package com.nhnacademy.shoppingmall.controller.order;

import com.nhnacademy.shoppingmall.cart.repository.impl.CartRepositoryImpl;
import com.nhnacademy.shoppingmall.cart.service.CartService;
import com.nhnacademy.shoppingmall.cart.service.impl.CartServiceImpl;
import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping.Method;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.common.util.AlertUtils;
import com.nhnacademy.shoppingmall.join.domain.ProductInfoInCart;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;
import com.nhnacademy.shoppingmall.useraddress.repository.impl.UserAddressRepositoryImpl;
import com.nhnacademy.shoppingmall.useraddress.service.UserAddressService;
import com.nhnacademy.shoppingmall.useraddress.service.impl.UserAddressServiceImpl;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method = Method.POST, value = "/user/order.do")
public class OrderController implements BaseController {

  private final CartService cartService = new CartServiceImpl(new CartRepositoryImpl());

  private final UserAddressService userAddressService = new UserAddressServiceImpl(new UserAddressRepositoryImpl());
  private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());
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

    List<String> addressList = userAddressService.getUserAddresses(userId);
    User user = userService.getUser(userId);
    if(Objects.isNull(addressList)|| Objects.isNull(user)) {
      return AlertUtils.alert(req, "오류가 발생하였습니다. 다시 시도해 주세요.", "/user/cart.do");
    }

    req.setAttribute("ORDER_LIST", orderList);
    req.setAttribute("TOTAL_PRICE", totalPrice);
    req.setAttribute("USER_ADDRESSES", addressList);
    req.setAttribute("USER", user);
    return "shop/order/order";
  }
}
