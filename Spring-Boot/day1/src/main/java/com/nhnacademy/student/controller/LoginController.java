package com.nhnacademy.student.controller;

import com.nhnacademy.student.domain.StudentLoginDTO;
import com.nhnacademy.student.domain.StudentLoginRequest;
import com.nhnacademy.student.entity.Student;
import com.nhnacademy.student.exception.ValidationFailedException;
import com.nhnacademy.student.repository.StudentRepository;
import com.nhnacademy.student.service.StudentManageService;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/login")
public class LoginController {

  private final StudentManageService studentManageService;

  public LoginController(StudentManageService studentManageService) {
    this.studentManageService = studentManageService;
  }

  @GetMapping
  public String getLogin(HttpServletRequest request) {
    // 로그인을 한 유저가 로그인 페이지를 다시 들어온 경우를 검사
    HttpSession session = request.getSession(false);
    if(Objects.nonNull(session) && (Objects.nonNull(session.getAttribute("STUDENT_ID")))) {
      return "redirect:/";
    }
    return "login";
  }

  @PostMapping
  public String postLogin(@Valid @ModelAttribute StudentLoginRequest studentLoginRequest, BindingResult bindingResult,
      HttpServletRequest request) {
    if(bindingResult.hasErrors())
      throw new ValidationFailedException("Validation Failed");
    Student student = studentManageService.getStudent(studentLoginRequest.getId());
    if(student.getName().equals(studentLoginRequest.getName())) {
      HttpSession session = request.getSession(true);
      session.setAttribute("STUDENT_ID", student.getId());
      log.debug("Login Success");

      return "redirect:/";
    }
    log.debug("Login Failed");
    return "login";
  }
}