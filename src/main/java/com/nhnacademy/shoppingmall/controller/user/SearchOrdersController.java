package com.nhnacademy.shoppingmall.controller.user;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping.Method;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.common.util.AlertUtils;
import com.nhnacademy.shoppingmall.order.domain.Order;
import com.nhnacademy.shoppingmall.order.repository.impl.OrderRepositoryImpl;
import com.nhnacademy.shoppingmall.order.service.OrderService;
import com.nhnacademy.shoppingmall.order.service.impl.OrderServiceImpl;
import com.nhnacademy.shoppingmall.orderdetails.domain.OrderDetails;
import com.nhnacademy.shoppingmall.orderdetails.service.OrderDetailsService;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method = Method.GET, value = "/user/mypage/orders.do")
public class SearchOrdersController implements BaseController {

  OrderService orderService = new OrderServiceImpl(new OrderRepositoryImpl());
  /**
   * @param req
   * @param resp
   * @return
   */
  @Override
  public String execute(HttpServletRequest req, HttpServletResponse resp) {
    HttpSession session = req.getSession(false);
    String userId = (String) session.getAttribute("USER_ID_SESSION");
    if(Objects.isNull(userId)) {
      return AlertUtils.alert(req, "유저 정보를 찾을 수 없습니다. 다시 시도해 주세요", "/user/mypage.do");
    }

    List<Order> orderList = orderService.getAllOrders(userId);
    if(Objects.isNull(orderList)) {
      return AlertUtils.alert(req, "오류가 발생했습니다. 다시 시도해 주세요", "/user/mypage.do");
    }

    req.setAttribute("ORDER_LIST", orderList);

    return "shop/user/func/search_orders";
  }
}
