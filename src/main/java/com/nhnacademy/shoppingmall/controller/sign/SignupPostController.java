package com.nhnacademy.shoppingmall.controller.sign;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping.Method;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method = Method.POST, value = "/signupAction.do")
public class SignupPostController implements BaseController {

  @Override
  public String execute(HttpServletRequest req, HttpServletResponse resp) {
    return null;
  }
}
