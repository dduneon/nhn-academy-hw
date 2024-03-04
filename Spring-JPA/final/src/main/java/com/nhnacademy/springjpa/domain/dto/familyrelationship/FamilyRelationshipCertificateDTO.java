package com.nhnacademy.springjpa.domain.dto.familyrelationship;

import com.nhnacademy.springjpa.domain.dto.residentregister.CertificateIssueDTO;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FamilyRelationshipCertificateDTO {
  private final CertificateIssueDTO certificateIssue;
  private final BaseResidentDTO resident;
  private final List<FamilyResidentDTO> familyResidents;
}
