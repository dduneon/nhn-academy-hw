package com.nhnacademy.springjpa.domain.dto.birth;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BirthReportResidentDTO {
  private final String name;
  private final String residentRegistrationNumber;
  private final String birthReportQualificationsCode;
  private final String emailAddress;
  private final String phoneNumber;
  private final LocalDate birthDeathReportDate;
}
