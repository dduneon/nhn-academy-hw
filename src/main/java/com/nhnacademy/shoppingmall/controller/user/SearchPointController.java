package com.nhnacademy.shoppingmall.controller.user;

import com.nhnacademy.shoppingmall.PointRecords.domain.PointRecords;
import com.nhnacademy.shoppingmall.PointRecords.repository.Impl.PointRecordsRepositoryImpl;
import com.nhnacademy.shoppingmall.PointRecords.service.Impl.PointRecordsServiceImpl;
import com.nhnacademy.shoppingmall.PointRecords.service.PointRecordsService;
import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping.Method;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.common.util.AlertUtils;
import com.nhnacademy.shoppingmall.order.domain.Order;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method = Method.GET, value = "/user/mypage/points.do")
public class SearchPointController implements BaseController {


  private final PointRecordsService pointRecordsService = new PointRecordsServiceImpl(new PointRecordsRepositoryImpl());
  /**
   * @param req
   * @param resp
   * @return
   */
  @Override
  public String execute(HttpServletRequest req, HttpServletResponse resp) {
    HttpSession session = req.getSession(false);
    String userId = (String) session.getAttribute("USER_ID_SESSION");
    if(Objects.isNull(userId)) {
      return AlertUtils.alert(req, "유저 정보를 찾을 수 없습니다. 다시 시도해 주세요", "/user/mypage.do");
    }

    List<PointRecords> recordList = pointRecordsService.getAllRecords(userId);
    if(Objects.isNull(recordList)) {
      return AlertUtils.alert(req, "오류가 발생했습니다. 다시 시도해 주세요", "/user/mypage.do");
    }

    req.setAttribute("RECORD_LIST", recordList);
    return "shop/user/func/search_point";
  }
}
