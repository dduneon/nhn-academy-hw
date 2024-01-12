package com.nhnacademy.springmvc.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Value;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

@Value
public class InquiryPostRequest {
  @NotBlank
  @NotNull
  String author;
  @NotNull
  @NotBlank
  @Length(min=2, max=200)
  String title;
  @NotBlank
  @NotNull
  String category;
  @NotBlank
  @NotNull
  @Length(min=0, max=40000)
  String content;
}
