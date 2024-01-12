package com.nhnacademy.springmvc.controller;

import com.nhnacademy.springmvc.domain.Role;
import com.nhnacademy.springmvc.domain.User;
import com.nhnacademy.springmvc.domain.UserLoginRequest;
import com.nhnacademy.springmvc.exception.PasswordNotCorrectException;
import com.nhnacademy.springmvc.exception.UserNotFoundException;
import com.nhnacademy.springmvc.repository.UserRepository;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/cs/login")
public class LoginController {
  private final UserRepository userRepository;

  public LoginController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @GetMapping
  public String getLogin(HttpServletRequest request) {
    // 이미 로그인되어 있는 경우를 체크하고, 메인 페이지로 redirect
    HttpSession session = request.getSession(false);
    return Objects.nonNull(session) && Objects.nonNull(session.getAttribute("userSession")) ? "redirect:/cs" : "login";
  }

  @PostMapping
  public String doLogin(@ModelAttribute UserLoginRequest userLoginRequest, HttpServletRequest request) {
    log.debug("postLogin(): called");
    log.debug("postLogin(): id({}), pw({})", userLoginRequest.getId(), userLoginRequest.getPassword());

    if(!userRepository.isExist(userLoginRequest.getId()))
      throw new UserNotFoundException();

    User user = userRepository.findById(userLoginRequest.getId());
    if(!user.getPassword().equals(userLoginRequest.getPassword())) {
      // Login Failure
      // todo throw exception or redirect?
      log.debug("postLogin(): Login failed");
      throw new PasswordNotCorrectException();
    }
    log.debug("postLogin(): Login success (Role: {})", user.getRole());
    HttpSession session = request.getSession(true);
    session.setAttribute("userSession", user);
    return (user.getRole() == Role.Customer) ? "redirect:/cs" : "redirect:/cs/admin";
  }
}
