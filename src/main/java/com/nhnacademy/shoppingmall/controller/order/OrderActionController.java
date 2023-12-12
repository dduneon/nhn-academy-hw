package com.nhnacademy.shoppingmall.controller.order;

import com.nhnacademy.shoppingmall.PointRecords.domain.PointRecords;
import com.nhnacademy.shoppingmall.PointRecords.repository.Impl.PointRecordsRepositoryImpl;
import com.nhnacademy.shoppingmall.PointRecords.service.Impl.PointRecordsServiceImpl;
import com.nhnacademy.shoppingmall.PointRecords.service.PointRecordsService;
import com.nhnacademy.shoppingmall.cart.repository.impl.CartRepositoryImpl;
import com.nhnacademy.shoppingmall.cart.service.CartService;
import com.nhnacademy.shoppingmall.cart.service.impl.CartServiceImpl;
import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping.Method;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.common.util.AlertUtils;
import com.nhnacademy.shoppingmall.join.domain.ProductInfoInCart;
import com.nhnacademy.shoppingmall.order.domain.Order;
import com.nhnacademy.shoppingmall.order.repository.impl.OrderRepositoryImpl;
import com.nhnacademy.shoppingmall.order.service.OrderService;
import com.nhnacademy.shoppingmall.order.service.impl.OrderServiceImpl;
import com.nhnacademy.shoppingmall.orderdetails.domain.OrderDetails;
import com.nhnacademy.shoppingmall.orderdetails.repository.impl.OrderDetailsRepositoryImpl;
import com.nhnacademy.shoppingmall.orderdetails.service.OrderDetailsService;
import com.nhnacademy.shoppingmall.orderdetails.service.impl.OrderDetailsServiceImpl;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method = Method.POST, value = "/user/order/orderAction.do")
public class OrderActionController implements BaseController {
  private final OrderService orderService = new OrderServiceImpl(new OrderRepositoryImpl());
  private final OrderDetailsService orderDetailsService = new OrderDetailsServiceImpl(new OrderDetailsRepositoryImpl());
  private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());
  private final CartService cartService = new CartServiceImpl(new CartRepositoryImpl());
  private final PointRecordsService pointRecordsService = new PointRecordsServiceImpl(new PointRecordsRepositoryImpl());
  @Override
  public String execute(HttpServletRequest req, HttpServletResponse resp) {
    HttpSession session = req.getSession(false);
    // 상품 구매 리스트
    List<ProductInfoInCart> buyingList = (List<ProductInfoInCart>) session.getAttribute("ORDER_LIST_SESSION");
    String userId = (String) session.getAttribute("USER_ID_SESSION");
    String shipAddress = req.getParameter("inputAddress");
    // null check
    if(Objects.isNull(buyingList) || Objects.isNull(userId) || Objects.isNull(shipAddress)) {
      return AlertUtils.alert(req, "정보를 받아오는 데에 실패하였습니다.", "/user/cart.do");
    }

    Order order = new Order(1, LocalDateTime.now(), LocalDateTime.now().plusDays(1), userId, shipAddress);
    // order save하고 orderId 받아옴(use Statement.RETURN_GENERATED_KEYS)
    int orderId = orderService.processOrderAndGetOrderId(order);
    log.debug("orderId: {}", orderId);

    int totalPrice = 0;

    // order page에서 가져온 구매 리스트 체크, 결제 도중 장바구니에 넣어도 결제 안되게
    for(ProductInfoInCart item: buyingList) {
      if(Objects.isNull(item)) {
        throw new RuntimeException("item is null");
      }
      OrderDetails orderDetails = new OrderDetails(orderId, item.getProductId(), item.getQuantity(), item.getUnitCost());
      orderDetailsService.processOrderDetail(orderDetails);
      cartService.deleteProductInCart(item.getProductId(), item.getUserId());
      totalPrice += item.getUnitCost() * item.getQuantity();
    }

    User user = userService.getUser(userId);
    if(Objects.isNull(user)) {
      throw new RuntimeException("user is null");
    }
    int userRemainPoint = user.getUserPoint()-totalPrice;
    log.debug("userId: {}, remain point: {}", userId, userRemainPoint);

    // 남은 포인트가 0보다 작으면 오류 발생, rollback
    if(userRemainPoint < 0) {
      throw new RuntimeException("userPoint not available");
    }

    // user point set
    user.setUserPoint(userRemainPoint);
    userService.updateUser(user);

    // point record add
    PointRecords pointRecords = new PointRecords(userId, -1 * totalPrice, orderId, LocalDateTime.now());

    pointRecordsService.saveRecord(pointRecords);

    // buying list session reset
    session.setAttribute("ORDER_LIST_SESSION", null);
    log.debug("order list reset: {}", Objects.isNull(session.getAttribute("ORDER_LIST_SESSION")));

    return AlertUtils.alert(req, "주문에 성공하였습니다.", "/index.do");
  }
}
