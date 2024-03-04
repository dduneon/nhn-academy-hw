package com.nhnacademy.shoppingmall.common.listener;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.domain.User.Auth;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebListener
public class ApplicationListener implements ServletContextListener {

  private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    DbConnectionThreadLocal.initialize();
    log.debug("ContextInitialized() called");
    //todo#12 application 시작시 테스트 계정인 admin,user 등록합니다. 만약 존재하면 등록하지 않습니다.
    User admin = new User("admin", "admin", "1234", "19991230", Auth.ROLE_ADMIN, 1000000,
        LocalDateTime.now(), null);
    User user = new User("user", "user", "1234", "20201020", Auth.ROLE_USER, 1000000,
        LocalDateTime.now(), null);
    if (Objects.isNull(userService.getUser("admin"))) {
      userService.saveUser(admin);
      log.debug("contextInitialized(), admin added");
    }
    if (Objects.isNull(userService.getUser("user"))) {
      userService.saveUser(user);
      log.debug("contextInitialized(), user added");
    }
    DbConnectionThreadLocal.reset();
  }
}
