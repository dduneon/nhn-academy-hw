package com.nhnacademy.springmvc.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

@Value
public class InquiryPostRequest {
  @NotNull
  @NotEmpty
  String author;
  @NotNull
  @NotEmpty
  @Length(min=2, max=200)
  String title;
  @NotNull
  @NotEmpty
  String category;
  @NotNull
  @NotEmpty
  @Length(min=0, max=40000)
  String content;
}