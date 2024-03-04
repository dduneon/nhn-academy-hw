package com.nhnacademy.shoppingmall.controller.auth;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.common.util.CookieUtils;
import java.util.Objects;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method = RequestMapping.Method.GET, value = "/logout.do")
public class LogoutController implements BaseController {

  //todo#13-3 로그아웃 구현
  @Override
  public String execute(HttpServletRequest req, HttpServletResponse resp) {
    HttpSession session = req.getSession(false);
    if (Objects.nonNull(session) && Objects.nonNull(session.getAttribute("USER_ID_SESSION"))) {
      log.debug("session invalidated");
      session.invalidate();
    }

    // Cookie sessionID 삭제
    Cookie cookie = CookieUtils.getCookie(req, "JSESSIONID");
    if (Objects.nonNull(cookie)) {
      cookie.setValue("");
      cookie.setMaxAge(0);
      resp.addCookie(cookie);
    }
    return "redirect:/index.do";
  }
}
