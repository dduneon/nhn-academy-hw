package com.nhnacademy.springmvc.hw.domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.Value;

@Value
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@RequiredArgsConstructor
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
