package com.nhnacademy.springmvc.controller;

import com.nhnacademy.springmvc.domain.Inquiry;
import com.nhnacademy.springmvc.domain.User;
import com.nhnacademy.springmvc.repository.InquiryRepository;
import java.util.TreeSet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

@Slf4j
@Controller
@RequestMapping("/cs")
public class CustomerMainController {
  private final InquiryRepository inquiryRepository;

  public CustomerMainController(InquiryRepository inquiryRepository) {
    this.inquiryRepository = inquiryRepository;
  }
  @GetMapping
  public String getInquiry(@SessionAttribute(name="userSession") User user, @RequestParam(name="category", defaultValue = "전체 보기") String category, Model model) {
    log.debug("getInquiry(): category -> {}, userId -> {}", category, user.getId());
    TreeSet<Inquiry> inquiries = inquiryRepository.findByUserIdWithCategory(user.getId(), category);

    model.addAttribute("currentCategory", category);
    model.addAttribute("user", user);
    model.addAttribute("inquiries", inquiries);
    log.debug("getInquiry(): total {} inquiry user({}) have", inquiries.size(), user.getId());
    return "main";
  }
}
