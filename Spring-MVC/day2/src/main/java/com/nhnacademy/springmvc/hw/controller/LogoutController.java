package com.nhnacademy.springmvc.hw.controller;

import java.util.Arrays;
import java.util.Objects;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/logout")
public class LogoutController {
  @GetMapping
  public String getLogout(HttpServletRequest request, HttpServletResponse response) {
    log.debug("getLogout() success");
    HttpSession session = request.getSession(false);
    if(Objects.isNull(session))
      return "login";

    Cookie cookie = Arrays.stream(request.getCookies()).filter(c -> c.getName().equals("JSESSION"))
            .findFirst().orElse(null);
    if(Objects.nonNull(cookie)) {
      cookie.setValue("");
      cookie.setMaxAge(0);
      response.addCookie(cookie);
    }
    session.invalidate();

    return "login";
  }

}
