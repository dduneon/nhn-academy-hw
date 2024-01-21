package com.nhnacademy.springjpa.domain.dto.familyrelationship;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BaseResidentDTO {
  private final String name;
  private final LocalDateTime birthDate;
  // localdatetime?
  private final String residentRegistrationNumber;
  private final String genderCode;
  private final String registrationBaseAddress;

  public String getBirthDate() {
    return birthDate.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일"));
  }

  public String getResidentRegistrationNumber() {
    return residentRegistrationNumber.substring(0, 6) + "-*******";
  }
}
