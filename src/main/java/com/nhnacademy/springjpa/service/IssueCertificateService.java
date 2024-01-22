package com.nhnacademy.springjpa.service;

import com.nhnacademy.springjpa.domain.dto.certlist.CertificationIssueDTO;
import com.nhnacademy.springjpa.domain.dto.familyrelationship.FamilyRelationshipCertificateDTO;
import com.nhnacademy.springjpa.domain.dto.residentregister.ResidentRegisterCertificateDTO;
import org.springframework.data.domain.Pageable;

public interface IssueCertificateService {
  ResidentRegisterCertificateDTO getResidentRegisterData(int serialNumber);
  FamilyRelationshipCertificateDTO getFamilyRelationshipData(int serialNumber);

  CertificationIssueDTO getCertificationList(int serialNumber, Pageable pageable);
  int getCertificationListCount(int serialNumber);

  }
