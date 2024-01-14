package com.nhnacademy.springmvc.controller;

import com.nhnacademy.springmvc.domain.Answer;
import com.nhnacademy.springmvc.domain.AnswerPostRequest;
import com.nhnacademy.springmvc.domain.Inquiry;
import com.nhnacademy.springmvc.domain.User;
import com.nhnacademy.springmvc.exception.InquiryNotFoundException;
import com.nhnacademy.springmvc.exception.ValidationFailedException;
import com.nhnacademy.springmvc.service.AdminAnswerService;
import com.nhnacademy.springmvc.service.AdminInquiryService;
import java.util.Objects;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

@Slf4j
@Controller
@RequestMapping("/cs/admin/answer")
public class AdminAnswerController {
  private final AdminInquiryService adminInquiryService;
  private final AdminAnswerService adminAnswerService;

  public AdminAnswerController(AdminInquiryService adminInquiryService,
      AdminAnswerService adminAnswerService) {
    this.adminInquiryService = adminInquiryService;
    this.adminAnswerService = adminAnswerService;
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
  public String postAdminAnswer(@Valid @ModelAttribute AnswerPostRequest answerPostRequest, BindingResult bindingResult
      , @SessionAttribute(name = "userSession") User admin) {
    log.debug("postAdminAnswer(): AnswerPostRequset -> ({}, {}, {})", answerPostRequest.getAuthor(), answerPostRequest.getInquiryId(), answerPostRequest.getAnswer());
    if(bindingResult.hasErrors())
      throw new ValidationFailedException(bindingResult);

    long inquiryId = answerPostRequest.getInquiryId();
    String author = answerPostRequest.getAuthor();
    String answerContent = answerPostRequest.getAnswer();

    Answer answer = new Answer(inquiryId, admin.getId(), answerContent);
    adminInquiryService.modifyAnsweredStatus(inquiryId, author);
    adminAnswerService.addInquiryAnswer(inquiryId, answer);
    return "redirect:/cs/admin";
  }
}
