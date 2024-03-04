package com.nhnacademy.student.controller;

import com.nhnacademy.student.domain.StudentRegisterRequest;
import com.nhnacademy.student.entity.Student;
import com.nhnacademy.student.exception.ValidationFailedException;
import com.nhnacademy.student.service.StudentManageService;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/student/register")
public class StudentRegisterController {

  private final StudentManageService studentManageService;

  public StudentRegisterController(StudentManageService studentManageService) {
    this.studentManageService = studentManageService;
  }

  @GetMapping
  public String studentRegisterForm() {
    return "student_register";
  }

  @PostMapping
  public ModelAndView registerStudent(@Valid @ModelAttribute StudentRegisterRequest studentRegisterRequest, BindingResult bindingResult) {
    if(bindingResult.hasErrors())
      throw new ValidationFailedException("Validation Failed");

    Student student = studentManageService.register(studentRegisterRequest);

    ModelAndView mav = new ModelAndView("redirect:/student/" + student.getId());
    mav.addObject("student", student);
    return mav;
  }
}