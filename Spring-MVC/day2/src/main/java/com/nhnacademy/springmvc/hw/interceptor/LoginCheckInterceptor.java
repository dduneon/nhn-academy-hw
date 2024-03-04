package com.nhnacademy.springmvc.hw.interceptor;

import com.nhnacademy.springmvc.hw.domain.Student;
import com.nhnacademy.springmvc.hw.domain.StudentLoginRequest;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    HttpSession session = request.getSession(false);
    // 세션이 null인 경우 검사
    if(Objects.isNull(session)) {
      log.debug("preHandle session result: null");
      response.sendRedirect("/login");
      return false;
    }
    StudentLoginRequest studentRequest = (StudentLoginRequest) session.getAttribute("student");
    log.debug("preHandle student result: " + !Objects.isNull(studentRequest));
    // 로그인을 하지 않은 경우를 검사
    if(Objects.isNull(studentRequest)) {
      response.sendRedirect("/login");
      return false;
    }
    return true;
  }
}
