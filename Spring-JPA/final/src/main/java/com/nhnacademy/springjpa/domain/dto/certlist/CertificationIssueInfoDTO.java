package com.nhnacademy.springjpa.domain.dto.certlist;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CertificationIssueInfoDTO {
  private final Long certificateConfirmationNumber;
  private final String certificateTypeCode;
  private final LocalDate certificateIssueDate;
}
