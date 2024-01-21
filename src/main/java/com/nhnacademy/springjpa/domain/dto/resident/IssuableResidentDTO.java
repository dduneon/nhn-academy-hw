package com.nhnacademy.springjpa.domain.dto.resident;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IssuableResidentDTO {
  private int residentSerialNumber;
  private String name;
  private Integer householdSerialNumber;
  private List<BirthDeathReportResidentDTO> birthDeathReportResident = new ArrayList<>();
  private List<CertificateIssueDTO> certificateIssue = new ArrayList<>();

  @NoArgsConstructor
  @EqualsAndHashCode
  @Setter
  @Getter
  public static class BirthDeathReportResidentDTO {
    private String birthDeathTypeCode;

    @Override
    public String toString() {
      return birthDeathTypeCode;
    }
  }

  @NoArgsConstructor
  @EqualsAndHashCode
  @Getter
  @Setter
  public static class CertificateIssueDTO {
    private Long certificateConfirmationNumber;

    @Override
    public String toString() {
      return certificateConfirmationNumber.toString();
    }
  }

}

