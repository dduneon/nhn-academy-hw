package com.nhnacademy.springjpa.domain.dto.page;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PagePosDTO {
  private final int navigationStartPos;
  private final int navigationEndPos;
}
