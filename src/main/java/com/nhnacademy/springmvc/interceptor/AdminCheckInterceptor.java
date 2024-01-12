package com.nhnacademy.springmvc.interceptor;

import com.nhnacademy.springmvc.domain.User;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class AdminCheckInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
//    HttpSession session = request.getSession(false);
//    User user = (User) session.getAttribute("userSession");
    User user = (User) request.getAttribute("userInfo");
    log.debug("AdminCheckInterceptor, User Info: {}", user.getId());
    return true;
    // todo check admin

  }
}
