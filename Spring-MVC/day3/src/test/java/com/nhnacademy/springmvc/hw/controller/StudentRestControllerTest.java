package com.nhnacademy.springmvc.hw.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.catchThrowable;

import com.nhnacademy.springmvc.hw.domain.Student;
import com.nhnacademy.springmvc.hw.exception.StudentNotFoundException;
import com.nhnacademy.springmvc.hw.exception.ValidationFailedException;
import com.nhnacademy.springmvc.hw.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.NestedServletException;


class StudentRestControllerTest {
  private MockMvc mockMvc;
  private StudentRepository studentRepository;

  private final String validJsonString = "{\"name\":\"test\",\"email\":\"test@test.com\",\"score\":10,\"comment\":\"test\"}";
  private final String inValidJsonString = "{\"name\":\"\",\"email\":\"\",\"score\":10,\"comment\":\"\"}";


  @BeforeEach
  void setUp() {
    studentRepository = mock(StudentRepository.class);
    mockMvc = MockMvcBuilders.standaloneSetup(new StudentRestController(studentRepository))
        .build();
  }

  @Test
  void createStudentValidationFailedTest() throws Exception {
    when(studentRepository.getStudent(anyLong())).thenReturn(null);

    Throwable th = catchThrowable(() -> mockMvc.perform(post("/students")
            .contentType(MediaType.APPLICATION_JSON)
            .content(inValidJsonString))
        .andExpect(status().isBadRequest()));

    assertThat(th).isInstanceOf(NestedServletException.class)
        .hasCauseInstanceOf(ValidationFailedException.class);
  }
  @Test
  void createStudentSuccessTest() throws Exception {
    Student student = new Student(2, "test", "test@test.com", 10, "test");
    when(studentRepository.register("test", "test@test.com", 10, "test"))
        .thenReturn(student);

    mockMvc.perform(post("/students")
        .contentType(MediaType.APPLICATION_JSON)
        .content(validJsonString))
        .andExpect(status().isCreated());
  }

  @Test
  void searchStudentNotFoundTest() {
    when(studentRepository.getStudent(anyLong())).thenReturn(null);
    Throwable th = catchThrowable(() -> mockMvc.perform(get("/students/{studentId}", 5))
        .andExpect(status().isForbidden()));

    assertThat(th).isInstanceOf(NestedServletException.class)
        .hasCauseInstanceOf(StudentNotFoundException.class);
  }

  @Test
  void searchStudentSuccessTest() throws Exception {
    Student student = new Student(2, "test", "test@test.com", 10, "test");
    when(studentRepository.getStudent(anyLong())).thenReturn(student);
     mockMvc.perform(get("/students/{studentId}", 2).header("Accept", "application/json"))
        .andExpect(status().isOk())
         .andExpect(MockMvcResultMatchers.content().string("{\"id\":2,\"name\":\"test\",\"email\":\"test@test.com\",\"score\":10,\"comment\":\"test\"}"));
  }

  @Test
  void modifyStudentVaildateFailedTest() {
    when(studentRepository.getStudent(anyLong())).thenReturn(null);

    Throwable th = catchThrowable(() -> mockMvc.perform(put("/students/{studentId}", 1)
            .contentType(MediaType.APPLICATION_JSON)
            .content(inValidJsonString))
        .andExpect(status().isBadRequest()));

    assertThat(th).isInstanceOf(NestedServletException.class)
        .hasCauseInstanceOf(ValidationFailedException.class);
  }

  @Test
  void modifyStudentNotFoundTest() {
    when(studentRepository.getStudent(anyLong())).thenReturn(null);

    Throwable th = catchThrowable(() -> mockMvc.perform(put("/students/{studentId}", 1)
        .content(validJsonString)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isForbidden()));

    assertThat(th).isInstanceOf(NestedServletException.class)
        .hasCauseInstanceOf(StudentNotFoundException.class);
  }

  @Test
  void modifyStudentSuccessTest() throws Exception {
    Student student = new Student(2, "test", "test@test.com", 10, "test");
    when(studentRepository.getStudent(2)).thenReturn(student);

    mockMvc.perform(put("/students/{studentId}", 2)
        .content(validJsonString)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }
}