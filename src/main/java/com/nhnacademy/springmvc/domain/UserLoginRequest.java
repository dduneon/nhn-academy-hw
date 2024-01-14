package com.nhnacademy.springmvc.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Value;

@Value
public class UserLoginRequest {
  @NotBlank
  @NotNull
  String id;
  @NotBlank
  @NotNull
  String password;
}
