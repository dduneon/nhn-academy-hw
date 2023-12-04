package com.nhnacademy.shoppingmall.common.filter;

import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.domain.User.Auth;
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

  @Override
  protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
      throws IOException, ServletException {
    //todo#11 /admin/ 하위 요청은 관리자 권한의 사용자만 접근할 수 있습니다. ROLE_USER가 접근하면 403 Forbidden 에러처리
    log.debug("{} called", getFilterName());
    HttpSession session = req.getSession(false);
    if (Objects.isNull(session)) {
      log.error("Session is null");
      res.sendError(403);
      return;
    } else {
      User user = (User) session.getAttribute("USER_SESSION");
      Auth auth = user.getUserAuth();
      log.debug("User Auth: {}", auth);
      if (auth.equals(Auth.ROLE_USER)) {
        log.error("Auth: {}, access admin page", auth);
        res.sendError(403);
        return;
      }
    }
    log.debug("Auth: Admin, doFilter call");
    chain.doFilter(req, res);
  }
}
