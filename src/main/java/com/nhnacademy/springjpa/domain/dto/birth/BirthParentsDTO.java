package com.nhnacademy.springjpa.domain.dto.birth;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BirthParentsDTO {
  private final String name;
  private final String residentRegistrationNumber;

  public String getResidentRegistrationNumber() {
    return residentRegistrationNumber.substring(0, 6) + "-*******";
  }
}