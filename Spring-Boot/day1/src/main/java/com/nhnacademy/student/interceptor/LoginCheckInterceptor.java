package com.nhnacademy.student.interceptor;

import com.nhnacademy.student.domain.StudentLoginRequest;
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
    Long studentId = (Long) session.getAttribute("STUDENT_ID");
    log.debug("preHandle student result: " + !Objects.isNull(studentId));
    // 로그인을 하지 않은 경우를 검사
    if(Objects.isNull(studentId)) {
      response.sendRedirect("/login");
      return false;
    }
    return true;
  }
}