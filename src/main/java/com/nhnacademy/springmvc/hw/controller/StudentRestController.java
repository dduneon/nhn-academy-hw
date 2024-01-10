package com.nhnacademy.springmvc.hw.controller;

import com.nhnacademy.springmvc.hw.domain.Student;
import com.nhnacademy.springmvc.hw.domain.StudentRegisterRequest;
import com.nhnacademy.springmvc.hw.exception.StudentNotFoundException;
import com.nhnacademy.springmvc.hw.exception.ValidationFailedException;
import com.nhnacademy.springmvc.hw.repository.StudentRepository;
import java.util.Objects;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
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
public class StudentRestController {
  private final StudentRepository studentRepository;

  public StudentRestController(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void createStudent(@Valid @RequestBody StudentRegisterRequest studentRegisterRequest, BindingResult bindingResult) {
    if(bindingResult.hasErrors())
      throw new ValidationFailedException(bindingResult);
    studentRepository.register(studentRegisterRequest.getName(), studentRegisterRequest.getEmail()
    ,studentRegisterRequest.getScore(), studentRegisterRequest.getComment());
  }

  @GetMapping("/{studentId}")
  public Student searchStudent(@PathVariable(name = "studentId") long studentId) {
    Student student = studentRepository.getStudent(studentId);
    if(Objects.isNull(student))
      throw new StudentNotFoundException();
    return student;
  }

  @PutMapping("/{studentId}")
  public void modifyStudent(@RequestBody StudentRegisterRequest studentRegisterRequest, @PathVariable(name="studentId") long studentId) {
    Student student = studentRepository.getStudent(studentId);
    if(Objects.isNull(student))
      throw new StudentNotFoundException();

    student.setName(studentRegisterRequest.getName());
    student.setEmail(studentRegisterRequest.getEmail());
    student.setScore(studentRegisterRequest.getScore());
    student.setComment(studentRegisterRequest.getComment());
    studentRepository.modify(student);
  }
}
