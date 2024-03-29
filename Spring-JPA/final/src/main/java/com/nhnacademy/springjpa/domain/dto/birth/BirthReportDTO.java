package com.nhnacademy.springjpa.domain.dto.birth;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BirthReportDTO {
  private final BornDTO born;
  private final BirthParentsDTO father;
  private final BirthParentsDTO mother;
  private final BirthReportResidentDTO reportResident;

  @AllArgsConstructor
  @Getter
  public static class BornDTO {
    private final String name;
    private final String genderCode;
    private final LocalDateTime birthDate;
    private final String birthPlaceCode;
    private final String registrationBaseAddress;

    public String getBirthDate() {
      return birthDate.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 hh시 mm분"));
    }
  }
}
