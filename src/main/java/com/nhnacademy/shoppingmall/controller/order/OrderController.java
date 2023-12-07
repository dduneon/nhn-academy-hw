package com.nhnacademy.shoppingmall.controller.order;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping.Method;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method = Method.GET, value = "/user/order.do")
public class OrderController implements BaseController {

  /**
   * @param req
   * @param resp
   * @return
   */
  @Override
  public String execute(HttpServletRequest req, HttpServletResponse resp) {
    return "shop/order/order";
  }
}
