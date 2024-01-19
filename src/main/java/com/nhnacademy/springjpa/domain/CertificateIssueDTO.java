package com.nhnacademy.springjpa.domain;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class CertificateIssueDTO {
  private Long certificateConfirmationNumber;

  @Override
  public String toString() {
    return certificateConfirmationNumber.toString();
  }
}
