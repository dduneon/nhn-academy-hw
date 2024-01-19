package com.nhnacademy.springjpa.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PagePosDTO {
  private final int navigationStartPos;
  private final int navigationEndPos;
}
