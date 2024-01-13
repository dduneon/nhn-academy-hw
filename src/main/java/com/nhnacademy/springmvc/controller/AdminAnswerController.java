package com.nhnacademy.springmvc.controller;

import com.nhnacademy.springmvc.domain.Inquiry;
import com.nhnacademy.springmvc.exception.InquiryNotFoundException;
import com.nhnacademy.springmvc.service.AdminInquiryService;
import java.util.Objects;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cs/admin/answer")
public class AdminAnswerController {
  private final AdminInquiryService adminInquiryService;

  public AdminAnswerController(AdminInquiryService adminInquiryService) {
    this.adminInquiryService = adminInquiryService;
  }

  @GetMapping("/{inquiryId}")
  public String getAdminAnswer(@PathVariable(name = "inquiryId") long inquiryId, Model model) {
    Inquiry inquiry = adminInquiryService.getInquiryById(inquiryId);
    if(Objects.isNull(inquiry))
      throw new InquiryNotFoundException();

    model.addAttribute("inquiry", inquiry);
    return "admin/answer";
  }

  @PostMapping
  public String postAdminAnswer() {
    // todo logic
    return "admin/answer";
  }

}
