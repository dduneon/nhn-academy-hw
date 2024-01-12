package com.nhnacademy.springmvc.controller;

import java.util.Arrays;
import java.util.Objects;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cs/logout")
public class LogoutController {
  @GetMapping
  public String getLogout(HttpServletRequest request, HttpServletResponse response) {
    HttpSession session = request.getSession(false);
    if(Objects.nonNull(session)) {
      session.invalidate();
      Cookie cookie = Arrays.stream(request.getCookies()).filter(c -> c.getName().equals("JSESSIONID"))
          .findFirst().orElse(null);
      if(Objects.nonNull(cookie)) {
        Cookie selectedCookie = new Cookie(cookie.getName(), null);
        selectedCookie.setMaxAge(0);
        selectedCookie.setPath("/");
        response.addCookie(cookie);
      }
    }
    return "redirect:/cs";
  }
}
