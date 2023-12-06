package com.nhnacademy.shoppingmall.common.page;

import java.util.List;

public class Page<T> {

  private final List<T> content;

  private final long totalCount;
  private final long totalPage;

  private final long currentIndex;
  public static final int PAGE_SIZE = 12;

  public Page(List<T> content, long totalCount, long currentIndex) {
    this.content = content;
    this.totalCount = totalCount;
    this.totalPage =
        (this.totalCount == 0) ? 1 : (int) Math.ceil((double) totalCount / (double) PAGE_SIZE);
    this.currentIndex = currentIndex;
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

  public long getCurrentIndex() {
    return currentIndex;
  }
}
