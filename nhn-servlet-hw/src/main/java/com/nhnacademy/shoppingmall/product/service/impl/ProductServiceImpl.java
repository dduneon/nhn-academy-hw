package com.nhnacademy.shoppingmall.product.service.impl;

import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.exception.ProductAlreadyExistException;
import com.nhnacademy.shoppingmall.product.exception.ProductNotFoundException;
import com.nhnacademy.shoppingmall.product.repository.ProductRepository;
import com.nhnacademy.shoppingmall.product.service.ProductService;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;

  public ProductServiceImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  /**
   * @param productId
   * @return
   */
  @Override
  public Product getProduct(int productId) {
    Optional<Product> product = productRepository.findByProductId(productId);
    log.debug("getProduct result is null? {}", Objects.isNull(product));
    return product.orElse(null);
  }

  /**
   * @param product
   */
  @Override
  public void saveProduct(Product product) {
    if (Objects.isNull(product)) {
      throw new RuntimeException("product is null");
    }
    if (Objects.nonNull(getProduct(product.getProductId()))) {
      throw new ProductAlreadyExistException("id: " + product.getProductId());
    }
    int result = productRepository.save(product);
    if (result < 1) {
      throw new RuntimeException("Cannot save product");
    }
  }

  /**
   * @param product
   */
  @Override
  public void updateProduct(Product product) {
    if (Objects.isNull(product)) {
      throw new RuntimeException("product is null");
    }
    if (Objects.isNull(getProduct(product.getProductId()))) {
      throw new ProductNotFoundException("id: " + product.getProductId());
    }
    int result = productRepository.update(product);
    if (result < 1) {
      throw new RuntimeException("Cannot update product");
    }
  }

  /**
   * @param product
   */
  @Override
  public void deleteProduct(int productId) {
    if (Objects.nonNull(getProduct(productId))) {
      throw new ProductNotFoundException("id: " + productId);
    }
    int result = productRepository.deleteByProductId(productId);
    if (result < 1) {
      throw new RuntimeException("Cannot update product");
    }
  }

  /**
   * @return
   */
  @Override
  public List<Product> getAllProductList() {
    return productRepository.getLimitedList(0, Integer.MAX_VALUE);
  }

  /**
   * @return
   */
  @Override
  public int getProductCount() {
    return productRepository.getProductCount();
  }

  /**
   * @return
   */
  @Override
  public Page<Product> getProductPage(long currentIndex, long row_count) {
    long offset = (currentIndex - 1) * Page.PAGE_SIZE;
    List<Product> products = productRepository.getLimitedList(offset, row_count);
    return new Page<>(products, productRepository.getProductCount(), currentIndex);
  }
}
