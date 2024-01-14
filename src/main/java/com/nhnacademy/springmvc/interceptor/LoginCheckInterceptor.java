package com.nhnacademy.springmvc.interceptor;

import com.nhnacademy.springmvc.domain.User;
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
    log.debug("LoginCheckInterceptor.preHandle(): session -> {}, attribute(userSession) -> {}", Objects.nonNull(session),
        Objects.nonNull(session) && Objects.nonNull(session.getAttribute("userSession")));
    if(Objects.isNull(session) || Objects.isNull(session.getAttribute("userSession"))) {
      response.sendRedirect("/cs/login");
      return false;
    }
    log.debug("LoginCheckInterceptor.preHandle(): userSession found");
    return true;
  }
}
