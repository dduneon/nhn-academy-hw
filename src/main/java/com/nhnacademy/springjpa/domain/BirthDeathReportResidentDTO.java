package com.nhnacademy.springjpa.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@EqualsAndHashCode
@Setter
@Getter
public class BirthDeathReportResidentDTO {
  private String birthDeathTypeCode;

  @Override
  public String toString() {
    return birthDeathTypeCode;
  }
}
