package com.nhnacademy.student.controller;

import com.nhnacademy.student.domain.StudentRegisterRequest;
import com.nhnacademy.student.entity.Student;
import com.nhnacademy.student.exception.StudentNotFoundException;
import com.nhnacademy.student.exception.ValidationFailedException;
import com.nhnacademy.student.service.StudentManageService;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequestMapping("/student")
public class StudentController {
  private final StudentManageService studentManageService;

  public StudentController(StudentManageService studentManageService) {
    this.studentManageService = studentManageService;
  }

  @ModelAttribute("student")
  public Student getStudent(@PathVariable("studentId") long studentId) {
    log.debug("getStudent method call");
    return studentManageService.getStudent(studentId);
  }

  @GetMapping("/{studentId}")
  public String viewStudent(@ModelAttribute Student student, Model model) {
    log.debug("viewStudent method call");
    model.addAttribute("student", student);
    return "student_view";
  }

  @GetMapping("/{studentId}/modify")
  public ModelAndView studentModifyForm(@ModelAttribute Student student) {
    ModelAndView mav = new ModelAndView("student_modify");
    mav.addObject("student", student);

    return mav;
  }

  @PostMapping("/{studentId}/modify")
  public String modifyStudent(@PathVariable long studentId, @Valid @ModelAttribute StudentRegisterRequest studentRequest, BindingResult bindingResult, ModelMap modelMap) {
    if(bindingResult.hasErrors()) {
      throw new ValidationFailedException("Validation Failed");
    }

    Student student = studentManageService.modify(studentId, studentRequest);

    modelMap.put("student", student);
    return "student_view";
  }

  @ExceptionHandler(StudentNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public String notFound(StudentNotFoundException ex, Model model) {
    model.addAttribute("exception", ex);
    return "error";
  }
}