package com.nhnacademy.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cs/admin")
public class AdminMainController {
  @GetMapping
  public String getAdminMain() {
    return "admin/main";
  }
}