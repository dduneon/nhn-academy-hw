package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.domain.dto.birth.BirthReportResidentDTO;
import com.nhnacademy.springjpa.domain.dto.death.DeathReportResidentDTO;
import com.nhnacademy.springjpa.entity.BirthDeathReportResident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BirthDeathReportResidentRepository extends JpaRepository<BirthDeathReportResident, BirthDeathReportResident.PK> {
  @Query("select new com.nhnacademy.springjpa.domain.dto.birth.BirthReportResidentDTO(r.name, r.residentRegistrationNumber, br.birthReportQualificationsCode, br.emailAddress, br.phoneNumber, br.birthDeathReportDate)"
   + " from BirthDeathReportResident br"
  + " left join Resident r"
  + " on br.pk.reportResidentSerialNumber = r.residentSerialNumber"
  + " where br.pk.residentSerialNumber = ?1 and br.pk.birthDeathTypeCode = '출생'")
  BirthReportResidentDTO findBirthReportResidentBySerialNumber(int serialNumber);

  @Query("select new com.nhnacademy.springjpa.domain.dto.death.DeathReportResidentDTO(r.name, r.residentRegistrationNumber, br.deathReportQualificationsCode, br.emailAddress, br.phoneNumber, br.birthDeathReportDate)"
      + " from BirthDeathReportResident br"
      + " left join Resident r"
      + " on br.pk.reportResidentSerialNumber = r.residentSerialNumber"
      + " where br.pk.residentSerialNumber = ?1 and br.pk.birthDeathTypeCode = '사망'")
  DeathReportResidentDTO findDeathReportResidentBySerialNumber(int serialNumber);
}
