package com.nhnacademy.shoppingmall.common.mvc.servlet;


import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;

@Slf4j
@WebServlet(name="jsonServlet", urlPatterns = {"/validate"})
public class JsonServlet extends HttpServlet {

  UserService userService = new UserServiceImpl(new UserRepositoryImpl());
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    try {
      DbConnectionThreadLocal.initialize();

      log.debug("jsonServlet called");
      String inputUserId = req.getHeader("user-id");
      log.debug("input id: {}", inputUserId);

      resp.setContentType("application/json");
      resp.setStatus(200);

      PrintWriter printWriter = resp.getWriter();
      JSONObject json = new JSONObject();

      boolean validation = Objects.isNull(userService.getUser(inputUserId));
      json.put("validation", validation);
      printWriter.write(json.toString());

    } catch (Exception e) {
      log.error("error: {}", e);
      resp.setStatus(500);
    } finally {
      DbConnectionThreadLocal.reset();
    }
  }
}
