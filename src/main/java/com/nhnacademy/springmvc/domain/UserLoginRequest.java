package com.nhnacademy.springmvc.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

//todo check
@AllArgsConstructor
@Getter
@NotBlank
@NotNull
public class UserLoginRequest {
  private String id;
  private String password;
}
