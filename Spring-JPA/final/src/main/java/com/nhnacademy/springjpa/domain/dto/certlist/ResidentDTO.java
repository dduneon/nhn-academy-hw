package com.nhnacademy.springjpa.domain.dto.certlist;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResidentDTO {
  private final int residentSerialNumber;
  private final String name;
  private final String residentRegistrationNumber;

  public String getResidentRegistrationNumber() {
    return residentRegistrationNumber.substring(0, 6) + "-*******";
  }
}
