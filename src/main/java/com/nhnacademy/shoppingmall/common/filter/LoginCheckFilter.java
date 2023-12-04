package com.nhnacademy.shoppingmall.common.filter;

import java.io.IOException;
import java.util.Objects;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/mypage/*")
public class LoginCheckFilter extends HttpFilter {

  @Override
  protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
      throws IOException, ServletException {
    //todo#10 /mypage/ 하위경로의 접근은 로그인한 사용자만 접근할 수 있습니다. - 잘 모르겠음 dofilter()부분 else에서만 ?
    log.debug("{} called", getFilterName());
    HttpSession session = req.getSession(false);
    if (Objects.isNull(session) || Objects.isNull(session.getAttribute("USER_ID_SESSION"))) {
      res.sendRedirect("/login.do");
      return;
    }
    chain.doFilter(req, res);
  }
}