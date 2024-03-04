package com.nhnacademy.springmvc.hw.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Student {
  @Getter
  private final long id;
  @Getter
  @Setter
  private String name;
  @Getter
  @Setter
  private String email;
  @Getter
  @Setter
  private int score;
  @Getter
  @Setter
  private String comment;

  public Student(long id, String name, String email, int score, String comment) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.score = score;
    this.comment = comment;
  }

  @Override
  public String toString() {
    return "Student{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", email='" + email + '\'' +
        ", score=" + score +
        ", comment='" + comment + '\'' +
        '}';
  }
}
