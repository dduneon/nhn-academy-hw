package com.nhnacademy.springmvc.hw.domain;

public class Student {
  private long id;
  private final String name;
  private final String email;
  private final int score;
  private final String comment;

  public Student(String name, String email, int score, String comment) {
    this.name = name;
    this.email = email;
    this.score = score;
    this.comment = comment;
  }
}
