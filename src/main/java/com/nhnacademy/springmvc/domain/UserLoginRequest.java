package com.nhnacademy.springmvc.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;

//todo check
@Value
@NotBlank
@NotNull
public class UserLoginRequest {
  String id;
  String password;
}
