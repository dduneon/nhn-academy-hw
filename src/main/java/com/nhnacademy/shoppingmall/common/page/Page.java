package com.nhnacademy.shoppingmall.common.page;

import java.util.List;

public class Page<T> {

  private final List<T> content;

  private final long totalCount;
  private final long totalPage;
  public static final int PAGE_SIZE = 12;

  public Page(List<T> content, long totalCount) {
    this.content = content;
    this.totalCount = totalCount;
    this.totalPage =
        (this.totalCount == 0) ? 1 : (int) Math.ceil((double) totalCount / (double) PAGE_SIZE);
  }

  public List<T> getContent() {
    return content;
  }

  public long getTotalCount() {
    return totalCount;
  }

  public long getTotalPage() {
    return totalPage;
  }
}
