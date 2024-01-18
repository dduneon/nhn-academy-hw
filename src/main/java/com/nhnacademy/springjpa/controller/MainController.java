package com.nhnacademy.springjpa.controller;

import com.nhnacademy.springjpa.entity.Product;
import com.nhnacademy.springjpa.entity.User;
import com.nhnacademy.springjpa.entity.User.Auth;
import com.nhnacademy.springjpa.service.ProductService;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Slf4j
@Controller
@RequestMapping("/")
public class MainController {
  private final ProductService productService;

  public MainController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping
  public String getMainPage(HttpSession session, Pageable pageable) {
    log.debug("{}", pageable);
    List<Product> productList = productService.getPageProducts(pageable).getContent();


    if(Objects.nonNull(session)) {
      User user = (User) session.getAttribute("userSession");
      if(Objects.nonNull(user) && user.getAuth() == Auth.ROLE_ADMIN)
        return "layout/admin";
    }
    return "layout/user";
  }
}
