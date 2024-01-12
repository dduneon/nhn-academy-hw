package com.nhnacademy.springmvc.interceptor;

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
    log.debug("LoginCheckInterceptor.preHandle(): called");
    HttpSession session = request.getSession(false);
    if(Objects.isNull(session) || Objects.isNull(session.getAttribute("userSession"))) {
      log.debug("LoginCheckInterceptor.preHandle(): session or attribute(userSession) is null");
      response.sendRedirect("/cs/login");
      return false;
    }
    log.debug("LoginCheckInterceptor.preHandle(): userSession found");
    return true;
  }
}
