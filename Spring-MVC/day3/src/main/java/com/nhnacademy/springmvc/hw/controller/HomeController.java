package com.nhnacademy.springmvc.hw.controller;

import com.nhnacademy.springmvc.hw.domain.StudentLoginRequest;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
  @GetMapping("/")
  public String index(HttpServletRequest request, Model model) {
    HttpSession session = request.getSession(false);
    boolean sessionState = Objects.nonNull(session);
    boolean loginState = false;

    if(sessionState) {
      StudentLoginRequest studentLoginRequest = (StudentLoginRequest) session.getAttribute(
          "student");
      loginState = Objects.nonNull(studentLoginRequest);
    }

    model.addAttribute("sessionState", sessionState);
    model.addAttribute("loginState", loginState);

    return "index";
  }

}
