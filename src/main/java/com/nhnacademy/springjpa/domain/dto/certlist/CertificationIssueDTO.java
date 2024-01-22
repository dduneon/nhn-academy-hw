package com.nhnacademy.springjpa.domain.dto.certlist;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
@AllArgsConstructor
public class CertificationIssueDTO {
  private final ResidentDTO resident;
  private final Page<CertificationIssueInfoDTO> certificationIssueInfo;
}
