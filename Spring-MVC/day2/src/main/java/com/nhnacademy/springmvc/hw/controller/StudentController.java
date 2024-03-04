package com.nhnacademy.springmvc.hw.controller;

import com.nhnacademy.springmvc.hw.domain.Student;
import com.nhnacademy.springmvc.hw.domain.StudentRegisterRequest;
import com.nhnacademy.springmvc.hw.exception.StudentNotFoundException;
import com.nhnacademy.springmvc.hw.exception.ValidationFailedException;
import com.nhnacademy.springmvc.hw.repository.StudentRepository;
import java.util.Objects;
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
  private final StudentRepository studentRepository;

  public StudentController(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  @ModelAttribute("student")
  public Student getStudent(@PathVariable("studentId") long studentId) {
    log.debug("getStudent method call");
    Student student = studentRepository.getStudent(studentId);
    if(Objects.isNull(student))
      throw new StudentNotFoundException();

    return student;
  }

  @GetMapping("/{studentId}")
  public String viewStudent(@ModelAttribute Student student, Model model) {
    log.debug("viewStudent method call");
    model.addAttribute("student", student);
    return "studentView";
  }

  @GetMapping(value = "/{studentId}", params = { "hideScore" })
  public String viewStudentWithoutScore(@ModelAttribute Student student, @RequestParam("hideScore") String isHide, Model model) {
    log.debug("viewStudentWithoutScore, isHide: " + isHide);
    model.addAttribute("student", student);
    return isHide.equals("yes") ? "studentViewHided" : "studentView";
  }


  @GetMapping("/{studentId}/modify")
  public ModelAndView studentModifyForm(@ModelAttribute Student student) {
    ModelAndView mav = new ModelAndView("studentModify");
    mav.addObject("student", student);

    return mav;
  }

  @PostMapping("/{studentId}/modify")
  public String modifyStudent(@PathVariable long studentId, @Valid @ModelAttribute StudentRegisterRequest studentRequest, BindingResult bindingResult, ModelMap modelMap) {
    if(bindingResult.hasErrors()) {
      throw new ValidationFailedException(bindingResult);
    }

    Student student = getStudent(studentId);

    student.setName(studentRequest.getName());
    student.setEmail(studentRequest.getEmail());
    student.setScore(studentRequest.getScore());
    student.setComment(studentRequest.getComment());

    studentRepository.modify(student);

    modelMap.put("student", student);
    return "studentView";
  }

  @ExceptionHandler(StudentNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public String notFound(StudentNotFoundException ex, Model model) {
    model.addAttribute("exception", ex);
    return "error";
  }

}
