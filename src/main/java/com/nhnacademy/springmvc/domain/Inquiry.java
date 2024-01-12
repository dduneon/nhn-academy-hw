package com.nhnacademy.springmvc.domain;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@Getter
public class Inquiry {
  private String author;
  private String title;
  private String category;
  private String content;
  private MultipartFile attachment;
  private LocalDateTime created;
  // todo change type
  private boolean isResponse;
}
