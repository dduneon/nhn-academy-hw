package com.nhnacademy.student.service.Impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.nhnacademy.student.domain.StudentRegisterRequest;
import com.nhnacademy.student.entity.Student;
import com.nhnacademy.student.exception.StudentAlreadyExistException;
import com.nhnacademy.student.exception.StudentNotFoundException;
import com.nhnacademy.student.repository.StudentRepository;
import com.nhnacademy.student.service.StudentManageService;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class StudentManageServiceImplTest {
  private StudentManageService studentManageService;
  private StudentRepository studentRepository;

  @BeforeEach
  void setUp() {
    studentRepository = mock(StudentRepository.class);
    studentManageService = new StudentManageServiceImpl(studentRepository);
  }
  @Test
  void getStudentExceptionThrowTest() {
    when(studentRepository.findById(anyLong())).thenReturn(null);
    assertThrows(StudentNotFoundException.class, () -> studentManageService.getStudent(anyLong()));
  }

  static Student student = new Student(1, "김준현", "test", 100, "test");
  @Test
  void getStudentTest() {
    when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
    Student actual = studentManageService.getStudent(1L);
    assertEquals(student, actual);
  }
  @Test
  void modifyAlreadyExist() {
    StudentRegisterRequest req = new StudentRegisterRequest("name", "email", 100, "comment");
    when(studentRepository.existsById(anyLong())).thenReturn(false);
    assertThrows(StudentAlreadyExistException.class, () -> studentManageService.modify(1L, req));
  }

  @Test
  void modifyTest() {
    StudentRegisterRequest req = new StudentRegisterRequest("name", "email", 100, "comment");
    when(studentRepository.existsById(anyLong())).thenReturn(true);
    when(studentRepository.save(any())).thenReturn(student);
    Student actual = studentManageService.modify(1L, req);
    assertEquals(student, actual);
  }
}