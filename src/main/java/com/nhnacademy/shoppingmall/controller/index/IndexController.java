package com.nhnacademy.shoppingmall.controller.index;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.product.service.ProductService;
import com.nhnacademy.shoppingmall.product.service.impl.ProductServiceImpl;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method = RequestMapping.Method.GET, value = {"/index.do"})
public class IndexController implements BaseController {

  private final ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());

  @Override
  public String execute(HttpServletRequest req, HttpServletResponse resp) {
    String currentIndexStr = req.getParameter("page");
    long currentIndex = (Objects.isNull(currentIndexStr) || currentIndexStr.isEmpty()) ? 1 :
        Integer.parseInt(currentIndexStr);

    Page<Product> currentPage = productService.getProductPage((currentIndex - 1) * Page.PAGE_SIZE,
        Page.PAGE_SIZE);

    if (Objects.isNull(currentPage) || currentPage.getContent().isEmpty()) {
      log.error("current page is null or empty list");
      throw new RuntimeException("current page is null or empty list");
    }
    log.debug("current page: {}, size: {}", currentIndex, currentPage.getContent().size());

    req.setAttribute("PAGE_CONTENTS", currentPage);

    return "shop/main/index";
  }
}