package com.nhnacademy.student.domain;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Value;

@Getter
@Value
public class StudentLoginRequest implements Serializable {
  @NotNull
  Long id;

  @NotBlank
  @NotNull
  String name;
}