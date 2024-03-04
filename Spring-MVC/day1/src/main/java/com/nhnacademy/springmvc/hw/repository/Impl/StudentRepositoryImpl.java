package com.nhnacademy.springmvc.hw.repository.Impl;

import com.nhnacademy.springmvc.hw.domain.Student;
import com.nhnacademy.springmvc.hw.exception.StudentNotFoundException;
import com.nhnacademy.springmvc.hw.repository.StudentRepository;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
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

    Student student = new Student(id, name, email, score, comment);
    log.debug("Student added: " + student);
    log.debug("size before put: " + students.size());
    students.put(id, student);
    log.debug("size after put: " + students.size());

    return student;
  }

  @Override
  public Student getStudent(long id) {
    return exists(id) ? students.get(id) : null;
  }

  @Override
  public void modify(Student student) {
    Student dbStudent = getStudent(student.getId());
    if(Objects.isNull(dbStudent))
      throw new StudentNotFoundException();

    dbStudent.setName(student.getName());
    dbStudent.setEmail(student.getEmail());
    dbStudent.setScore(student.getScore());
    dbStudent.setComment(student.getComment());
  }
}
