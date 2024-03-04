package com.nhnacademy.springmvc.domain;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

@Value
public class AnswerPostRequest {
  @NotEmpty
  @NotNull
  String author;
  long inquiryId;
  @NotNull
  @Length(min=1, max=40000)
  String answer;
}
