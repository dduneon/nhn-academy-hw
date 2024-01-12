package com.nhnacademy.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cs/admin/answer")
public class AdminAnswerController {
  @GetMapping
  public String getAdminAnswer() {
    return "admin/answer";
  }

  @PostMapping
  public String postAdminAnswer() {
    // todo logic
    return "admin/answer";
  }

}
