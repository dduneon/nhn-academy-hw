package com.nhnacademy.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cs/inquiry")
public class CustomerInquiryController {
  @GetMapping
  public String getInquiry() {
    return "customer/inquiry";
  }

  @PostMapping
  public String createInquiry() {
    return "customer/inquiry";
  }
}
