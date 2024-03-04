package com.nhnacademy.shoppingmall.controller.product;

import com.nhnacademy.shoppingmall.categoryproduct.repository.impl.CategoryProductRepositoryImpl;
import com.nhnacademy.shoppingmall.categoryproduct.service.CategoryProductService;
import com.nhnacademy.shoppingmall.categoryproduct.service.impl.CategoryProductServiceImpl;
import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.common.util.AlertUtils;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.product.service.ProductService;
import com.nhnacademy.shoppingmall.product.service.impl.ProductServiceImpl;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method = RequestMapping.Method.GET, value = "/product/detail.do")
public class ProductDetailController implements BaseController {

  private final ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());
  private final CategoryProductService categoryProductService = new CategoryProductServiceImpl(new CategoryProductRepositoryImpl());
  /**
   * @param req
   * @param resp
   * @return
   */
  @Override
  public String execute(HttpServletRequest req, HttpServletResponse resp) {
    String productIdStr = req.getParameter("id");
    log.debug("Requst parameter(id): {}", productIdStr);
    if (Objects.isNull(productIdStr)) {
      log.error("ProductId is null");
      // popup alert
      return AlertUtils.alert(req, "잘못된 상품 페이지 접근입니다.", "/index.do");
    }

    int productId = Integer.parseInt(productIdStr);
    Product product = productService.getProduct(productId);
    List<String> categories = categoryProductService.getCategoriesName(productId);

    req.setAttribute("PRODUCT", product);
    req.setAttribute("PRODUCT_CATEGORY", categories);
    return "shop/product/detail";
  }
}
