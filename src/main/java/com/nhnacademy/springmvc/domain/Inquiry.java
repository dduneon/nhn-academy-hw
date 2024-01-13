package com.nhnacademy.springmvc.domain;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@Getter
public class Inquiry {
  private long id;
  private String author;
  private String title;
  private String category;
  private String content;
  private MultipartFile[] attachment;
  private LocalDateTime created;
  @Setter
  private boolean isAnswered = false;

  public Inquiry(long id, InquiryPostRequest inquiryPostRequest, MultipartFile[] files) {
    this.id = id;
    this.author = inquiryPostRequest.getAuthor();
    this.title = inquiryPostRequest.getTitle();
    this.category = inquiryPostRequest.getCategory();
    this.content = inquiryPostRequest.getContent();
    this.attachment = files;
    this.created = LocalDateTime.now();
  }
}
