package com.nhnacademy.springmvc.hw.domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Value;

@Value
public class StudentRegisterRequest {
  @NotBlank
  String name;
  @Email
  String email;
  @Min(0)
  @Max(100)
  int score;
  @Size(min = 1, max = 200)
  String comment;
}
