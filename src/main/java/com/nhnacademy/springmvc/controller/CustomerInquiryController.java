package com.nhnacademy.springmvc.controller;

import com.nhnacademy.springmvc.domain.Answer;
import com.nhnacademy.springmvc.domain.Inquiry;
import com.nhnacademy.springmvc.domain.InquiryPostRequest;
import com.nhnacademy.springmvc.domain.User;
import com.nhnacademy.springmvc.exception.FileUploadFailedException;
import com.nhnacademy.springmvc.exception.FilenameExtensionNotSupportedException;
import com.nhnacademy.springmvc.exception.ValidationFailedException;
import com.nhnacademy.springmvc.repository.InquiryRepository;
import com.nhnacademy.springmvc.service.CustomerInquiryService;
import com.nhnacademy.springmvc.util.FileCheckUtils;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Controller
@RequestMapping("/cs/inquiry")
public class CustomerInquiryController {
  private final CustomerInquiryService customerInquiryService;

  public CustomerInquiryController(CustomerInquiryService customerInquiryService) {
    this.customerInquiryService = customerInquiryService;
  }

  @GetMapping
  public String getInquiry(@SessionAttribute(name="userSession") User user, Model model) {
    log.debug("getInquiry(): user in session (userId) -> {}", user.getId());
    model.addAttribute("user", user);
    return "customer/inquiry";
  }

  @PostMapping
  public String createInquiry(@Valid @ModelAttribute InquiryPostRequest inquiryPostRequest, BindingResult bindingResult, @RequestParam(name="attachment", required = false) MultipartFile[] files, Model model){
    log.debug("createInquiry(): {}, {}, {}, {}", inquiryPostRequest.getAuthor(), inquiryPostRequest.getTitle()
        , inquiryPostRequest.getCategory(), inquiryPostRequest.getContent());
    if(bindingResult.hasErrors())
      throw new ValidationFailedException(bindingResult);

    customerInquiryService.addUserInquiry(inquiryPostRequest, files);
    return "redirect:/cs";
  }

  @GetMapping("/{inquiryId}")
  public String getInquiryDetail(@PathVariable(name="inquiryId") long inquiryId,@SessionAttribute("userSession") User user, Model model) {
    Inquiry inquiry = customerInquiryService.getSpecifiedInquiry(user.getId(), inquiryId);
    Answer answer = customerInquiryService.getSpecifiedAnswer(inquiryId);

    model.addAttribute("inquiry", inquiry);
    if(Objects.nonNull(answer))
      model.addAttribute("answer", answer);
    return "customer/inquiryDetail";
  }
}
