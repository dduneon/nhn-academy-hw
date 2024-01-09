package com.nhnacademy.springmvc.hw.domain;

import lombok.Value;

@Value
public class StudentRegisterRequest {
  long id;
  String name;
  String email;
  int score;
  String comment;
}
