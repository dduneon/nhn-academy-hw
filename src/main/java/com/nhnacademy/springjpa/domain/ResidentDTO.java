package com.nhnacademy.springjpa.domain;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResidentDTO {
  private int residentSerialNumber;
  private String name;
  private List<BirthDeathReportResidentDTO> birthDeathReportResident = new ArrayList<>();
  private List<CertificateIssueDTO> certificateIssue = new ArrayList<>();
}

