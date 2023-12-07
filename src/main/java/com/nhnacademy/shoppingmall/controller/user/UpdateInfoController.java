package com.nhnacademy.shoppingmall.controller.user;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping.Method;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.common.util.AlertUtils;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method = Method.GET, value = "/user/mypage/info.do")
public class UpdateInfoController implements BaseController {

  private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());

  /**
   * @param req
   * @param resp
   * @return
   */
  @Override
  public String execute(HttpServletRequest req, HttpServletResponse resp) {
    HttpSession session = req.getSession(false);
    if (Objects.isNull(session)) {
      // popup alert
      return AlertUtils.alert(req, "세션이 만료되어 접근할 수 없습니다. 다시 로그인해 주세요", "/login.do");

    }
    String userId = (String) session.getAttribute("USER_ID_SESSION");
    if (Objects.isNull(userId)) {
      // popup alert
      session.invalidate();
      return AlertUtils.alert(req, "세션이 만료되어 접근할 수 없습니다. 다시 로그인해 주세요", "/login.do");
    }
    log.debug("Session userID: {}", userId);
    User user = userService.getUser(userId);
    if (Objects.isNull(user)) {
      return AlertUtils.alert(req, "오류가 발생하였습니다.", "/index.do");
    }
    log.debug("success to set attribute user");
    req.setAttribute("USER_REQ", user);
    return "shop/user/func/update_info";
  }
}
