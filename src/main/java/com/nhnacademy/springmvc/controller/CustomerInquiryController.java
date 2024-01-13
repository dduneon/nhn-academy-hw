package com.nhnacademy.springmvc.controller;

import com.nhnacademy.springmvc.domain.Inquiry;
import com.nhnacademy.springmvc.domain.InquiryPostRequest;
import com.nhnacademy.springmvc.domain.User;
import com.nhnacademy.springmvc.exception.FileUploadFailedException;
import com.nhnacademy.springmvc.exception.FilenameExtensionNotSupportedException;
import com.nhnacademy.springmvc.exception.ValidationFailedException;
import com.nhnacademy.springmvc.repository.InquiryRepository;
import com.nhnacademy.springmvc.util.FileCheckUtils;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Date;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
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
  private static final String UPLOAD_DIR = "/Users/dduneon/Documents/test";

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
  public String createInquiry(@Valid @ModelAttribute InquiryPostRequest inquiryPostRequest, BindingResult bindingResult, @RequestParam(name="attachment", required = false) MultipartFile[] files, Model model){
    log.debug("createInquiry(): {}, {}, {}, {}", inquiryPostRequest.getAuthor(), inquiryPostRequest.getTitle()
        , inquiryPostRequest.getCategory(), inquiryPostRequest.getContent());
    if(bindingResult.hasErrors())
      throw new ValidationFailedException(bindingResult);

    log.debug("createInquiry(): number of upload file -> {}", files.length);
    for(MultipartFile file: files) {
      log.debug("createInquiry(): fileName -> {}", file.getOriginalFilename());
      if(!StringUtils.hasText(file.getOriginalFilename()))
        break;
      try {
        log.debug("{}", file.getOriginalFilename());
        if(FileCheckUtils.CheckFilenameExtension(file.getOriginalFilename())) {
          file.transferTo(Paths.get(UPLOAD_DIR + file.getOriginalFilename()));
        } else {
          throw new FilenameExtensionNotSupportedException();
        }
      } catch (IOException ioException) {
        throw new FileUploadFailedException();
      }
    }

    inquiryRepository.save(inquiryPostRequest, files);
    return "redirect:/cs";
  }
}
