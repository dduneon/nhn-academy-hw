package com.nhnacademy.springmvc.controller;

import com.nhnacademy.springmvc.domain.Answer;
import com.nhnacademy.springmvc.domain.AnswerPostRequest;
import com.nhnacademy.springmvc.domain.Inquiry;
import com.nhnacademy.springmvc.domain.User;
import com.nhnacademy.springmvc.exception.InquiryNotFoundException;
import com.nhnacademy.springmvc.exception.ValidationFailedException;
import com.nhnacademy.springmvc.service.AdminAnswerService;
import com.nhnacademy.springmvc.service.AdminInquiryService;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

@Slf4j
@Controller
@RequestMapping("/cs/admin/answer")
public class AdminAnswerController {
  private final AdminInquiryService adminInquiryService;
  private final AdminAnswerService adminAnswerService;

  @Value("${UPLOAD_DIR}")
  private String downloadPath;

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

  @PostMapping("/download")
  @ResponseBody
  public ResponseEntity<Resource> downloadFile(@RequestParam(name="downloadFilename") String fileName, @RequestParam(name="downloadInquiryId") long inquiryId,
      @RequestParam(name="downloadInquiryAuthor") String author) throws IOException {
    String path = downloadPath + author + File.separator + inquiryId + File.separator + fileName;
    Resource resource = new FileSystemResource(path);

    HttpHeaders headers = new HttpHeaders();
    headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));

    return ResponseEntity.ok()
        .headers(headers)
        .contentLength(resource.getFile().length())
        .contentType(MediaType.APPLICATION_OCTET_STREAM)
        .body(resource);
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
