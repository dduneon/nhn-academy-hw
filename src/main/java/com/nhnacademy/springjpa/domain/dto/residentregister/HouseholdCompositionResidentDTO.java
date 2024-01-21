package com.nhnacademy.springjpa.domain.dto.residentregister;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HouseholdCompositionResidentDTO {
  private final String householdRelationshipCode;
  private final String name;
  private final String residentRegistrationNumber;
  private final LocalDate reportDate;
  private final String householdCompositionChangeReasonCode;

  public String getResidentRegistrationNumber() {
    return residentRegistrationNumber.substring(0, 6) + "-*******";
  }
}
