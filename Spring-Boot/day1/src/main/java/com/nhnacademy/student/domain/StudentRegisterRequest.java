package com.nhnacademy.student.domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;

@Value
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Setter
public class StudentRegisterRequest {
  @NotBlank
  @NotNull
  String name;
  @NotBlank
  @NotNull
  @Email
  String email;
  @Min(0)
  @Max(100)
  int score;
  @NotNull
  @Size(min = 1, max = 200)
  String comment;
}