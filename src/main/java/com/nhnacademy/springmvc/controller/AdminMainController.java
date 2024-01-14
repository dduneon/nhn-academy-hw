package com.nhnacademy.springmvc.controller;

import com.nhnacademy.springmvc.domain.Inquiry;
import com.nhnacademy.springmvc.domain.User;
import com.nhnacademy.springmvc.repository.InquiryRepository;
import com.nhnacademy.springmvc.service.AdminInquiryService;
import java.util.List;
import java.util.TreeSet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Slf4j
@Controller
@RequestMapping("/cs/admin")
public class AdminMainController {
  private final AdminInquiryService adminInquiryService;

  public AdminMainController(AdminInquiryService adminInquiryService) {
    this.adminInquiryService = adminInquiryService;
  }

  @GetMapping
  public String getAdminMain(@SessionAttribute(name = "userSession") User user, Model model) {
    log.debug("getAdminMain(): called");
    List<Inquiry> notRespondInquiries = adminInquiryService.getNotRespondInquiries();

    model.addAttribute("user", user);
    model.addAttribute("notRespondInquiries", notRespondInquiries);
    return "admin/main";
  }
}