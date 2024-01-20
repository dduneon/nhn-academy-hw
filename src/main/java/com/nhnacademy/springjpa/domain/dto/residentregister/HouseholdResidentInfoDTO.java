package com.nhnacademy.springjpa.domain.dto.residentregister;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HouseholdResidentInfoDTO {
  private final String name;
  private final String householdCompositionReasonCode;
  private final LocalDate householdCompositionDate;

}
