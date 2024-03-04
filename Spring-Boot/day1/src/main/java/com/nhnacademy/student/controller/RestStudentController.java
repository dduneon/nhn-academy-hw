package com.nhnacademy.student.controller;

import com.nhnacademy.student.domain.StudentRegisterRequest;
import com.nhnacademy.student.entity.Student;
import com.nhnacademy.student.exception.StudentNotFoundException;
import com.nhnacademy.student.exception.ValidationFailedException;
import com.nhnacademy.student.repository.StudentRepository;
import com.nhnacademy.student.service.StudentManageService;
import java.util.Objects;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class RestStudentController {
  private final StudentManageService studentManageService;

  public RestStudentController(StudentManageService studentManageService) {
    this.studentManageService = studentManageService;
  }
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void createStudent(@Valid @RequestBody StudentRegisterRequest studentRegisterRequest, BindingResult bindingResult, HttpServletResponse response) {
    if(bindingResult.hasErrors()) {
      response.setStatus(400);
      throw new ValidationFailedException("Validation Failed");
    }
    studentManageService.register(studentRegisterRequest);
  }

  @GetMapping("/{studentId}")
  @ResponseStatus(HttpStatus.OK)
  public Student searchStudent(@PathVariable(name = "studentId") long studentId) {
    return studentManageService.getStudent(studentId);
  }

  @PutMapping("/{studentId}")
  @ResponseStatus(HttpStatus.OK)
  public void modifyStudent(@PathVariable Long studentId, @Valid @RequestBody StudentRegisterRequest studentRegisterRequest, BindingResult bindingResult, HttpServletResponse response) {
    if(bindingResult.hasErrors()) {
      response.setStatus(400);
      throw new ValidationFailedException("Validation Failed");
    }
    studentManageService.modify(studentId, studentRegisterRequest);
  }
}