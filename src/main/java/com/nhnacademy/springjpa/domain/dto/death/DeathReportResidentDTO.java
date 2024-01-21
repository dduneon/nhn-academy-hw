package com.nhnacademy.springjpa.domain.dto.death;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

  public String getResidentRegistrationNumber() {
    return residentRegistrationNumber.substring(0, 6) + "-*******";
  }

  public String getBirthDeathReportDate() {
    return birthDeathReportDate.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일"));
  }
}
