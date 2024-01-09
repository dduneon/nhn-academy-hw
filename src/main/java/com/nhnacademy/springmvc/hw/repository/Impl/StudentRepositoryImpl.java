package com.nhnacademy.springmvc.hw.repository.Impl;

import com.nhnacademy.springmvc.hw.domain.Student;
import com.nhnacademy.springmvc.hw.repository.StudentRepository;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.stereotype.Repository;

@Repository
public class StudentRepositoryImpl implements StudentRepository {
  private final Map<Long, Student> students = new HashMap<>();

  public boolean exists(long id) {
    return students.containsKey(id);
  }

  @Override
  public Student register(String name, String email, int score, String comment) {
    long id = students.keySet()
        .stream()
        .max(Comparator.comparing(Function.identity()))
        .map(l -> l + 1)
        .orElse(1L);

    Student student = new Student(name, email, score, comment);
    students.put(id, student);

    return student;
  }

  @Override
  public Student getStudent(long id) {
    return exists(id) ? students.get(id) : null;
  }
}
