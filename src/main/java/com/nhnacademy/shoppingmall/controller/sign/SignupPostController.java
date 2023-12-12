package com.nhnacademy.shoppingmall.controller.sign;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping.Method;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.common.util.AlertUtils;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.domain.User.Auth;
import com.nhnacademy.shoppingmall.user.exception.UserAlreadyExistsException;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method = Method.POST, value = "/signupAction.do")
public class SignupPostController implements BaseController {

  private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());

  @Override
  public String execute(HttpServletRequest req, HttpServletResponse resp) {
    String inputId = req.getParameter("user_id");
    String inputPw = req.getParameter("user_password");
    String inputName = req.getParameter("user_name");
    String inputBirth = req.getParameter("user_birth");
    log.debug("(signup req) userid: {}, userpw: {}, username: {}, userbirth: {}", inputId, inputPw,
        inputName, inputBirth);

    // null check
    if (Objects.isNull(inputId) || Objects.isNull(inputPw) || Objects.isNull(inputName)
        || Objects.isNull(inputBirth)) {
      return "redirect:/login.do";
    }

    // null check
    if(inputId.isEmpty() || inputPw.isEmpty() || inputName.isEmpty() || inputBirth.isEmpty()) {
      return "redirect:/login.do";
    }

    // user instance
    User user = new User(inputId, inputName, inputPw, inputBirth.replace("-", ""), Auth.ROLE_USER,
        1000000,
        LocalDateTime.now(), null);

    // register and try login
    try {
      userService.saveUser(user);
      userService.doLogin(user.getUserId(), user.getUserPassword());
      HttpSession session = req.getSession(true);
      session.setMaxInactiveInterval(60 * 60);
      session.setAttribute("USER_ID_SESSION", user.getUserId());
      session.setAttribute("USER_AUTH_SESSION", user.getUserAuth());
    } catch (UserAlreadyExistsException userAlreadyExistsException) {
      log.error("Signup Failed: {}", userAlreadyExistsException.getMessage());
      // popup alert
      return AlertUtils.alert(req, "이미 존재하는 아이디 입니다. 아이디를 변경해 주세요.", "/signup.do");
    } catch (RuntimeException runtimeException) {
      log.error("Signup Failed: {}", runtimeException.getMessage());
      // popup alert
      return AlertUtils.alert(req, "회원가입에 실패하였습니다. 다시 시도해주세요.", "/signup.do");
    }
    return "redirect:/index.do";
  }
}
