package com.nhnacademy.springmvc.controller;

import com.nhnacademy.springmvc.domain.Inquiry;
import com.nhnacademy.springmvc.domain.User;
import com.nhnacademy.springmvc.repository.InquiryRepository;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cs/inquiry")
public class CustomerInquiryController {
  private final InquiryRepository inquiryRepository;

  public CustomerInquiryController(InquiryRepository inquiryRepository) {
    this.inquiryRepository = inquiryRepository;
  }

  @GetMapping
  public String getInquiry(HttpServletRequest request, Model model) {
    return "customer/inquiry";
  }

  @PostMapping
  public String createInquiry() {
    return "customer/inquiry";
  }
}
