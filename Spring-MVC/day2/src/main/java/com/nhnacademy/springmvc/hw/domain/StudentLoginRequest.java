package com.nhnacademy.springmvc.hw.domain;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Value;

@Getter
@Value
public class StudentLoginRequest implements Serializable {
  @NotBlank
  long id;

  @NotBlank
  String name;
}
