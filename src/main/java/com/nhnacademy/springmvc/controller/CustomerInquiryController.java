package com.nhnacademy.springmvc.controller;

import com.nhnacademy.springmvc.domain.InquiryPostRequest;
import com.nhnacademy.springmvc.domain.User;
import com.nhnacademy.springmvc.exception.ValidationFailedException;
import com.nhnacademy.springmvc.repository.InquiryRepository;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Controller
@RequestMapping("/cs/inquiry")
public class CustomerInquiryController {
  private static final String UPLOAD_DIR = "C:\\Users\\S20184366\\Documents\\test\\";

  private final InquiryRepository inquiryRepository;

  public CustomerInquiryController(InquiryRepository inquiryRepository) {
    this.inquiryRepository = inquiryRepository;
  }

  @GetMapping
  public String getInquiry(@SessionAttribute(name="userSession") User user, Model model) {
    log.debug("getInquiry(): user in session (userId) -> {}", user.getId());
    model.addAttribute("user", user);
    return "customer/inquiry";
  }

  @PostMapping
  public String createInquiry(@Valid @ModelAttribute InquiryPostRequest inquiryPostRequest, @RequestParam(name="attachment") MultipartFile file, BindingResult bindingResult, Model model) throws IOException {
    log.debug("createInquiry(): inquiry request is null?{}", Objects.isNull(inquiryPostRequest));
    log.debug("createInquiry(): {}, {}, {}, {}", inquiryPostRequest.getAuthor(), inquiryPostRequest.getTitle()
    , inquiryPostRequest.getCategory(), inquiryPostRequest.getContent());
    if(bindingResult.hasErrors())
      throw new ValidationFailedException(bindingResult);
    MultipartFile uploadFile = file;
    uploadFile.transferTo(Paths.get(UPLOAD_DIR + uploadFile.getOriginalFilename()));

    model.addAttribute("fileName", uploadFile.getOriginalFilename());
    model.addAttribute("size", uploadFile.getSize());
    return "test";
  }
}
