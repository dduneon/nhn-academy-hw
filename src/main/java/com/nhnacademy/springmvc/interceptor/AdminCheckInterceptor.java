package com.nhnacademy.springmvc.interceptor;

import com.nhnacademy.springmvc.domain.Role;
import com.nhnacademy.springmvc.domain.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class AdminCheckInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    User user = (User) request.getSession(false).getAttribute("userSession");
    log.debug("AdminCheckInterceptor, User Info: {}", user.getId());
    if(user.getRole() == Role.Customer) {
      //todo exception?
      response.sendRedirect("/cs");
      return false;
    }
    return true;
  }
}
