package com.nhnacademy.springmvc.domain;


import javax.validation.constraints.NotNull;
import lombok.NonNull;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

@Value
@NonNull
public class AnswerPostRequest {
  String author;
  long inquiryId;
  @NotNull
  @Length(min=1, max=40000)
  String answer;
}
