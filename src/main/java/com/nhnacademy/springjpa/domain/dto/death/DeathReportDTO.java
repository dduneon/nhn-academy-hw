package com.nhnacademy.springjpa.domain.dto.death;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DeathReportDTO {
  private final DeadDTO dead;
  private final DeathReportResidentDTO deathReportResident;

  @AllArgsConstructor
  @Getter
  public static class DeadDTO {
    private final String name;
    private final String residentRegistrationNumber;
    private final LocalDateTime deathDate;
    private final String deathPlaceCode;
    private final String deathPlaceAddress;
  }
}
