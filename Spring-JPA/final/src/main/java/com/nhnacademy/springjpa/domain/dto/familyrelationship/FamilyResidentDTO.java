package com.nhnacademy.springjpa.domain.dto.familyrelationship;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FamilyResidentDTO {
  private final String familyRelationshipCode;
  private final String name;
  private final LocalDateTime birthDate;
  private final String residentRegistrationNumber;
  private final String genderCode;

  public String getBirthDate() {
    return birthDate.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일"));
  }

  public String getResidentRegistrationNumber() {
    return residentRegistrationNumber.substring(0, 6) + "-*******";
  }
}
