package com.nhnacademy.springmvc.hw.repository;

import com.nhnacademy.springmvc.hw.domain.Student;

public interface StudentRepository {
  boolean exists(long id);

  Student register(String name, String email, int score, String comment);

  Student getStudent(long id);

  void modify(Student student);
}
