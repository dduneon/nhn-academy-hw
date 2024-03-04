package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.domain.dto.birth.BirthReportDTO.BornDTO;
import com.nhnacademy.springjpa.domain.dto.certlist.ResidentDTO;
import com.nhnacademy.springjpa.domain.dto.death.DeathReportDTO.DeadDTO;
import com.nhnacademy.springjpa.domain.dto.familyrelationship.BaseResidentDTO;
import com.nhnacademy.springjpa.entity.Resident;
import com.nhnacademy.springjpa.repository.custom.ResidentRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResidentRepository extends ResidentRepositoryCustom, JpaRepository<Resident, Integer> {
  BornDTO findBornByResidentSerialNumber(int residentSerialNumber);
  DeadDTO findDeadByResidentSerialNumber(int residentSerialNumber);
  BaseResidentDTO findBaseResidentByResidentSerialNumber(int residentSerialNumber);
  ResidentDTO findResidentByResidentSerialNumber(int residentSerialNumber);

}
