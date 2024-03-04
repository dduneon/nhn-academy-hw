package com.nhnacademy.shoppingmall.common.page;

import java.util.List;

public class Page<T> {

  private final List<T> content;

  private final long totalCount;
  private final long totalPage;

  private final long currentIndex;

  private final long startPage;
  private final long endPage;

  public static final int MAX_VISIBLE_PAGE = 10;
  public static final int PAGE_SIZE = 12;

  public Page(List<T> content, long totalCount, long currentIndex) {
    this.content = content;
    this.totalCount = totalCount;
    this.totalPage =
        (this.totalCount == 0) ? 1 : (int) Math.ceil((double) totalCount / (double) PAGE_SIZE);
    this.currentIndex = currentIndex;
    this.startPage = Math.max(1, currentIndex - MAX_VISIBLE_PAGE / 2);
    this.endPage = Math.min(totalPage, startPage + MAX_VISIBLE_PAGE - 1);
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

  public long getStartPage() {
    return startPage;
  }

  public long getEndPage() {
    return endPage;
  }
}
