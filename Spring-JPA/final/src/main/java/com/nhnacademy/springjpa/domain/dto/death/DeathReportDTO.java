package com.nhnacademy.springjpa.domain.dto.death;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    public String getBirthDate() {
      return deathDate.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 hh시 mm분"));
    }

    public String getResidentRegistrationNumber() {
      return residentRegistrationNumber.substring(0, 6) + "-*******";
    }
  }
}
