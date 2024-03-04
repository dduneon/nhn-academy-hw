package com.nhnacademy.springmvc.domain;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class Answer {
  private final long inquiryId;
  private final String answeredAdminId;
  private final String content;
  private final LocalDateTime answered;

  public Answer(long inquiryId, String answeredAdminId, String content) {
    this.inquiryId = inquiryId;
    this.answeredAdminId = answeredAdminId;
    this.content = content;
    this.answered = LocalDateTime.now();
  }
}