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
@RequestMapping(method = Method.POST, value = "/user/mypage/infoAction.do")
public class UpdateInfoPostController implements BaseController {

  private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());

  /**
   * @param req
   * @param resp
   * @return
   */
  @Override
  public String execute(HttpServletRequest req, HttpServletResponse resp) {
    String name = req.getParameter("name");
    String pastPassword = req.getParameter("past_password");
    String newPassword = req.getParameter("new_password");
    String birthday = req.getParameter("birthday");

    log.debug("name: {}, past_pw: {}, new_pw: {}, birth: {}", name, pastPassword, newPassword,
        birthday);
    if (Objects.isNull(name) || Objects.isNull(pastPassword) || Objects.isNull(newPassword)
        || Objects.isNull(birthday)) {
      return AlertUtils.alert(req, "잘못 입력되었습니다. 다시 입력해 주세요", "/user/mypage/info.do");
    }

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
      return AlertUtils.alert(req, "정보를 가져오는 데에 오류가 발생하였습니다.", "/index.do");
    }
    if (!pastPassword.equals(user.getUserPassword())) {
      return AlertUtils.alert(req, "이전 비밀번호를 잘못 입력하셨습니다. 다시 입력해주세요", "/user/mypage/info.do");
    }
    user.setUserName(name);
    user.setUserPassword(newPassword);
    user.setUserBirth(birthday.replace("-", ""));

    userService.updateUser(user);
    // 세션 초기화를 위한 재 로그인 요청
    session.invalidate();
    return AlertUtils.alert(req, "회원 정보 변경에 성공하였습니다. 변경된 회원 정보로 다시 로그인해 주세요", "/index.do");
  }
}
