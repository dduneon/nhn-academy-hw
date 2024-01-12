package com.nhnacademy.springmvc.controller;

import com.nhnacademy.springmvc.domain.Inquiry;
import com.nhnacademy.springmvc.domain.User;
import com.nhnacademy.springmvc.repository.InquiryRepository;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/cs")
public class CustomerMainController {
  private final InquiryRepository inquiryRepository;

  public CustomerMainController(InquiryRepository inquiryRepository) {
    this.inquiryRepository = inquiryRepository;
  }
  @GetMapping
  public String getInquiry(HttpServletRequest request, Model model) {
    HttpSession session = request.getSession(false);
    User user = (User) session.getAttribute("userSession");
    List<Inquiry> inquiryList = inquiryRepository.findById(user.getId());

    model.addAttribute("user", user);
    model.addAttribute("inquiryList", inquiryList);
    log.debug("getInquiry(): total {} inquiry user({}) have", inquiryList.size(), user.getId());
    return "main";
  }
}
