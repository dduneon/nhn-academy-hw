package com.nhnacademy.springjpa.service.Impl;

import com.nhnacademy.springjpa.domain.dto.birth.BirthReportDTO;
import com.nhnacademy.springjpa.domain.dto.birth.BirthReportDTO.BornDTO;
import com.nhnacademy.springjpa.domain.dto.birth.BirthParentsDTO;
import com.nhnacademy.springjpa.domain.dto.birth.BirthReportResidentDTO;
import com.nhnacademy.springjpa.domain.dto.death.DeathReportDTO;
import com.nhnacademy.springjpa.domain.dto.death.DeathReportDTO.DeadDTO;
import com.nhnacademy.springjpa.domain.dto.death.DeathReportResidentDTO;
import com.nhnacademy.springjpa.domain.dto.residentregister.ResidentRegisterDTO;
import com.nhnacademy.springjpa.repository.BirthDeathReportResidentRepository;
import com.nhnacademy.springjpa.repository.FamilyRelationshipRepository;
import com.nhnacademy.springjpa.repository.ResidentRepository;
import com.nhnacademy.springjpa.service.BirthDeathReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BirthDeathReportServiceImpl implements BirthDeathReportService {
  private final ResidentRepository residentRepository;
  private final FamilyRelationshipRepository familyRelationshipRepository;
  private final BirthDeathReportResidentRepository birthDeathReportResidentRepository;

  public BirthDeathReportServiceImpl(ResidentRepository residentRepository,
      FamilyRelationshipRepository familyRelationshipRepository,
      BirthDeathReportResidentRepository birthDeathReportResidentRepository) {
    this.residentRepository = residentRepository;
    this.familyRelationshipRepository = familyRelationshipRepository;
    this.birthDeathReportResidentRepository = birthDeathReportResidentRepository;
  }

  public BirthReportDTO getBirthReportData(int serialNumber) {
    BornDTO born = residentRepository.findBornByResidentSerialNumber(serialNumber);
    BirthParentsDTO father = familyRelationshipRepository.findRelationParentsBySerialNumber(serialNumber, "부");
    BirthParentsDTO mother = familyRelationshipRepository.findRelationParentsBySerialNumber(serialNumber, "모");
    BirthReportResidentDTO reportResident = birthDeathReportResidentRepository.findBirthReportResidentBySerialNumber(serialNumber);
    // logging start
    log.debug("getBirthReportData(): born name -> {}", born.getName());
      log.debug("getBirthReportData(): parents name -> {}", father.getName());
    log.debug("getBirthReportData(): parents name -> {}", mother.getName());
    log.debug("getBirthReportData(): report name -> {}", reportResident.getName());
    // logging end
    return new BirthReportDTO(born, father, mother, reportResident);
  }

  public DeathReportDTO getDeathReportData(int serialNumber) {
    DeadDTO dead = residentRepository.findDeadByResidentSerialNumber(serialNumber);
    DeathReportResidentDTO reportResident = birthDeathReportResidentRepository.findDeathReportResidentBySerialNumber(serialNumber);
    // logging start
    log.debug("getDeathReportData(): dead name -> {}", dead.getName());
    log.debug("getDeathReportData(): report name -> {}", reportResident.getName());
    // logging end
    return new DeathReportDTO(dead, reportResident);
  }
}
