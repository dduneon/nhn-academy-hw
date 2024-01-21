package com.nhnacademy.springjpa.service;

import com.nhnacademy.springjpa.domain.dto.familyrelationship.FamilyRelationshipCertificateDTO;
import com.nhnacademy.springjpa.domain.dto.residentregister.ResidentRegisterCertificateDTO;

public interface IssueCertificateService {
  ResidentRegisterCertificateDTO getResidentRegisterData(int serialNumber);
  FamilyRelationshipCertificateDTO getFamilyRelationshipData(int serialNumber);

  }
