package com.nhnacademy.springjpa.domain.dto.death;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DeathReportResidentDTO {
  private final String name;
  private final String residentRegistrationNumber;
  private final String deathReportQualificationsCode;
  private final String emailAddress;
  private final String phoneNumber;
  private final LocalDate birthDeathReportDate;
}
