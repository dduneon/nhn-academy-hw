package com.nhnacademy.springmvc.hw.controller;

import com.nhnacademy.springmvc.hw.domain.Student;
import com.nhnacademy.springmvc.hw.domain.StudentLoginRequest;
import com.nhnacademy.springmvc.hw.repository.StudentRepository;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequestMapping("/login")
public class LoginController {
  private final StudentRepository studentRepository;

  public LoginController(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  @GetMapping
  public String getLogin() {
    return "login";
  }

  @PostMapping
  public String postLogin(@ModelAttribute StudentLoginRequest studentLoginRequest,
      HttpServletRequest request) {
    Student student = studentRepository.getStudent(studentLoginRequest.getId());
    if(Objects.nonNull(student) && student.getName().equals(studentLoginRequest.getName())) {
      HttpSession session = request.getSession(true);
      session.setAttribute("student", studentLoginRequest);
      log.debug("Login Success");

      return "redirect:student/" + studentLoginRequest.getId();
    }
    log.debug("Login Failed");
    return "login";
  }
}
