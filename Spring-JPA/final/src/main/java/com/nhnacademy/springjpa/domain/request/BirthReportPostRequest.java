package com.nhnacademy.springjpa.domain.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class BirthReportPostRequest {
  @NotNull
  Integer reportResidentSerialNumber;
  @NotNull
  @JsonFormat(pattern = "yyyy-MM-dd")
  LocalDate birthDeathReportDate;
  @NotNull
  @NotEmpty
  String birthReportQualificationsCode;
  @Email
  String emailAddress;
  @NotNull
  @NotEmpty
  String phoneNumber;
}
