package com.nhnacademy.shoppingmall.controller.auth;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.exception.UserNotFoundException;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method = RequestMapping.Method.POST, value = "/loginAction.do")
public class LoginPostController implements BaseController {

  private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());

  @Override
  public String execute(HttpServletRequest req, HttpServletResponse resp) {
    //todo#13-2 로그인 구현, session은 60분동안 유지됩니다.
    String userId = req.getParameter("user_id");
    String userPw = req.getParameter("user_password");
    log.debug("ID: {}, PW: {}", userId, userPw);

    if (Objects.nonNull(userId) && Objects.nonNull(userPw)) {
      try {
        User user = userService.doLogin(userId, userPw);
        HttpSession session = req.getSession(true);
        session.setMaxInactiveInterval(60 * 60);
        session.setAttribute("USER_ID_SESSION", user.getUserId());
        session.setAttribute("USER_AUTH_SESSION", user.getUserAuth());
      } catch (UserNotFoundException userNotFoundException) {
        log.error("user not found, redirect to login.do");
        return "redirect:/login.do";
      }
      return "shop/main/index";
    }
    log.error("id or pw is null, redirect to login.do");
    return "redirect:/login.do";
  }
}
