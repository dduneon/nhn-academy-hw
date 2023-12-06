package com.nhnacademy.shoppingmall.controller.user;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping.Method;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method = Method.GET, value = "/user/mypage/address.do")
public class MagageAddressController implements BaseController {

  /**
   * @param req
   * @param resp
   * @return
   */
  @Override
  public String execute(HttpServletRequest req, HttpServletResponse resp) {
    return "shop/user/mypage/manage_address";
  }
}
