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
  public String getLogout(HttpSession session) {
    if(Objects.nonNull(session)) {
      session.removeAttribute("userSession");
      session.invalidate();
    }
    return "redirect:/cs/login";
  }
}
