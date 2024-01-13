package com.nhnacademy.springmvc.controller;

import com.nhnacademy.springmvc.domain.Inquiry;
import com.nhnacademy.springmvc.domain.User;
import com.nhnacademy.springmvc.repository.InquiryRepository;
import java.util.TreeSet;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequestMapping("/cs/admin")
public class AdminMainController {
  private final InquiryRepository inquiryRepository;

  public AdminMainController(InquiryRepository inquiryRepository) {
    this.inquiryRepository = inquiryRepository;
  }

  @GetMapping
  public String getAdminMain(@SessionAttribute(name = "userSession") User user, Model model) {
    TreeSet<Inquiry> notRespondedInquiries = inquiryRepository.getNotRespondedInquiry();

    model.addAttribute("user", user);
    model.addAttribute("notRespondedInquiries", notRespondedInquiries);
    return "admin/main";
  }
}