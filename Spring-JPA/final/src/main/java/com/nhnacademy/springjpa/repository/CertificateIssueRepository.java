package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.domain.dto.certlist.CertificationIssueInfoDTO;
import com.nhnacademy.springjpa.entity.CertificateIssue;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateIssueRepository extends JpaRepository<CertificateIssue, Long> {
  List<CertificationIssueInfoDTO> findByResident_ResidentSerialNumber(int residentSerialNumber);

  int countByResident_ResidentSerialNumber(int residentSerialNumber);
}
