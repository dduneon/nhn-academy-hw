package com.nhnacademy.springjpa.controller;

import com.nhnacademy.springjpa.domain.ProductDTO;
import com.nhnacademy.springjpa.exception.PageNotFoundException;
import com.nhnacademy.springjpa.service.CategoryService;
import com.nhnacademy.springjpa.service.ProductService;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequestMapping("/")
public class MainController {
  private final ProductService productService;

  private static final int DEFAULT_PAGE_SIZE = 9;
  private static final int DEFAULT_NAVIGATION_SIZE = 10;


  public MainController(ProductService productService) {
    this.productService = productService;
  }
  @GetMapping
  public String getPageProducts(@PageableDefault(page=0, size = DEFAULT_PAGE_SIZE) Pageable pageable, Model model) {
    Page<ProductDTO> pageContent = productService.getPageProducts(pageable);
    int totalProductCount = productService.getProductCount();
    return getPageInfo(pageable, model, pageContent, totalProductCount);
  }

  @GetMapping("/search")
  public String getPageProductsWithName(@RequestParam(name="name") String name, @PageableDefault(page=0, size = DEFAULT_PAGE_SIZE) Pageable pageable, Model model) {
    Page<ProductDTO> pageContent = productService.getPageProductsByName(pageable, name);
    for(ProductDTO p: pageContent.getContent()) {
      log.debug("getPAgeProductsWithName(): name->{}", p.getName());
    }
    log.debug("size->{}", pageContent.getContent().size());
    model.addAttribute("NAME", name);
    int totalProductCount = productService.getProductCountByName(name);
    return getPageInfo(pageable, model, pageContent, totalProductCount);
  }

  private String getPageInfo(
      @PageableDefault(page = 0, size = DEFAULT_PAGE_SIZE) Pageable pageable,
      Model model, Page<ProductDTO> pageContent, int totalProductCount) {

    int currentNavigationOffset = pageable.getPageNumber() / DEFAULT_NAVIGATION_SIZE;
    int maxNavigationPosition = totalProductCount / pageable.getPageSize()
        - (totalProductCount % pageable.getPageSize() == 0 ? 1 : 0);
    maxNavigationPosition = Math.max(maxNavigationPosition, 0);

    int navigationStartPosition = 10 * currentNavigationOffset;
    int navigationEndPosition = 9 + (currentNavigationOffset * 10);

    if(navigationStartPosition > maxNavigationPosition || pageable.getPageNumber() > maxNavigationPosition) {
      throw new PageNotFoundException();
    }
    if(navigationEndPosition > maxNavigationPosition) {
      navigationEndPosition = maxNavigationPosition;
    }

    log.debug("getPageInfo(): start->{}, end->{}, current->{}, max->{}", navigationStartPosition, navigationEndPosition, pageContent.getPageable().getPageNumber(), maxNavigationPosition);

    model.addAttribute("PAGE_CONTENT", pageContent);
    model.addAttribute("NAVIGATION_START_POS", navigationStartPosition);
    model.addAttribute("NAVIGATION_END_POS", navigationEndPosition);

    return "index";
  }
}
