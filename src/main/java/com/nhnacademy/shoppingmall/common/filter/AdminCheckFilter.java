package com.nhnacademy.shoppingmall.common.filter;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.domain.User.Auth;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;
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
@WebFilter(filterName = "adminCheckFilter", urlPatterns = "/admin/*")
public class AdminCheckFilter extends HttpFilter {

  private final UserServiceImpl userService = new UserServiceImpl(new UserRepositoryImpl());

  @Override
  protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
      throws IOException, ServletException {
    //todo#11 /admin/ 하위 요청은 관리자 권한의 사용자만 접근할 수 있습니다. ROLE_USER가 접근하면 403 Forbidden 에러처리
    log.debug("{} called", getFilterName());
    HttpSession session = req.getSession(false);
    DbConnectionThreadLocal.initialize();
    if (Objects.isNull(session)) {
      log.error("Session is null");
      DbConnectionThreadLocal.reset();
      res.sendError(403);
      return;
    }
    String userId = (String) session.getAttribute("USER_ID_SESSION");
    User user = userService.getUser(userId);
    if (Objects.isNull(user) || user.getUserAuth().equals(Auth.ROLE_USER)) {
      log.error("Cannot Access by ROLE_USER or Non login state");
      DbConnectionThreadLocal.reset();
      res.sendError(403);
      return;
    }
    log.debug("Auth: Admin, doFilter call");
    DbConnectionThreadLocal.reset();
    chain.doFilter(req, res);
  }
}
