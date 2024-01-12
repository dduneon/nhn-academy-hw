package com.nhnacademy.springmvc.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
public class User implements Serializable {
  private String id;
  private String password;
  private String name;
  private Role role;
}
