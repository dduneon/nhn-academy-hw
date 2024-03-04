package com.nhnacademy.springmvc.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class User implements Serializable {
  private String id;
  private String password;
  private String name;
  private Role role;
}
