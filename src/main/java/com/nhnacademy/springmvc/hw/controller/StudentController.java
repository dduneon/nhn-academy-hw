package com.nhnacademy.springmvc.hw.controller;

import com.nhnacademy.springmvc.hw.domain.Student;
import com.nhnacademy.springmvc.hw.exception.StudentNotFoundException;
import com.nhnacademy.springmvc.hw.repository.StudentRepository;
import java.util.Objects;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {
  private final StudentRepository studentRepository;

  public StudentController(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  @GetMapping("/{studentId}")
  public String viewStudent(@PathVariable long studentId, Model model) {
    Student student = studentRepository.getStudent(studentId);
    if(Objects.isNull(student))
      throw new StudentNotFoundException();

    model.addAttribute("student", student);
    return "studentView";
  }

  @GetMapping("/{studentId}/modify")
  public String studentModifyForm() {
    return "studentModify";
  }

  @PostMapping("/{studentId}/modify")
  public String modifyStudent() {
    return "studentView";
  }

}
