package com.nhnacademy.springjpa.repository.custom;

import com.nhnacademy.springjpa.domain.dto.familyrelationship.FamilyRelationshipCertificateDTO;
import com.nhnacademy.springjpa.domain.dto.familyrelationship.FamilyResidentDTO;
import java.util.List;

public interface FamilyRelationshipRepositoryCustom {
  List<FamilyResidentDTO> getRelationalFamilyResident(int baseResidentSerialNumber);
}
