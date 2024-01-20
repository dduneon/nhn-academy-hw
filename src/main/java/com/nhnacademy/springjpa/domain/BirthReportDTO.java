package com.nhnacademy.springjpa.domain;

import java.time.LocalDateTime;

public class BirthReportDTO {
  public static class BornerDTO {
    private String name;
    private String genderCode;
    private LocalDateTime birthDate;
    private String birthPlaceCode;
    private String registrationBaseAddress;
  }

  public static class ParentsDTO {
    private String fatherName;
    private String fatherRegistrationNumber;
    private String motherName;
    private String motherRegistrationNumber;
  }

  public static class ReportResidentDTO {
    private String name;
    private String residentRegistrationNumber;
    private String birthReportQualificationsCode;
  }
}
