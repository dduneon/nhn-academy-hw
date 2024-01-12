package com.nhnacademy.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cs/logout")
public class LogoutController {
  @GetMapping
  public String getLogout() {
    return "redirect:/cs";
  }
}
