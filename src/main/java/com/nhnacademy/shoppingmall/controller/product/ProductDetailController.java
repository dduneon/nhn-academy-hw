package com.nhnacademy.shoppingmall.controller.product;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.common.util.AlertUtils;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.product.service.ProductService;
import com.nhnacademy.shoppingmall.product.service.impl.ProductServiceImpl;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method = RequestMapping.Method.GET, value = "/product/detail.do")
public class ProductDetailController implements BaseController {

  private final ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());

  /**
   * @param req
   * @param resp
   * @return
   */
  @Override
  public String execute(HttpServletRequest req, HttpServletResponse resp) {
    String productId = req.getParameter("id");
    log.debug("Requst parameter(id): {}", productId);
    if (Objects.isNull(productId)) {
      return "redirect:/index.do";
    }
    Product product = productService.getProduct(Integer.parseInt(productId));
    if (Objects.isNull(productId)) {
      log.error("ProductId is null {}", productId);
      // popup alert
      return AlertUtils.alert(req, "잘못된 상품 페이지 접근입니다.", "/index.do");
    }
    req.setAttribute("PRODUCT", product);
    return "shop/product/detail";
  }
}
