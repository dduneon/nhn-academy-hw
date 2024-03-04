package com.nhnacademy.springjpa.domain.dto.residentregister;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CertificateIssueDTO {
  private final LocalDate certificateIssueDate;
  private final long certificationConfirmationNumber;

  public String getCertificationConfirmationNumber() {
    String number = String.valueOf(certificationConfirmationNumber);
    return number.substring(0, 8) + "-" + number.substring(8);
  }
}
