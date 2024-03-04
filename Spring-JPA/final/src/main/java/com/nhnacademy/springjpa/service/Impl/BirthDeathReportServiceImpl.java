package com.nhnacademy.springjpa.service.Impl;

import com.nhnacademy.springjpa.domain.dto.birth.BirthReportDTO;
import com.nhnacademy.springjpa.domain.dto.birth.BirthReportDTO.BornDTO;
import com.nhnacademy.springjpa.domain.dto.birth.BirthParentsDTO;
import com.nhnacademy.springjpa.domain.dto.birth.BirthReportResidentDTO;
import com.nhnacademy.springjpa.domain.dto.death.DeathReportDTO;
import com.nhnacademy.springjpa.domain.dto.death.DeathReportDTO.DeadDTO;
import com.nhnacademy.springjpa.domain.dto.death.DeathReportResidentDTO;
import com.nhnacademy.springjpa.domain.request.BirthReportPostRequest;
import com.nhnacademy.springjpa.domain.request.BirthReportPutRequest;
import com.nhnacademy.springjpa.domain.request.DeathReportPostRequest;
import com.nhnacademy.springjpa.domain.request.DeathReportPutRequest;
import com.nhnacademy.springjpa.entity.BirthDeathReportResident;
import com.nhnacademy.springjpa.entity.BirthDeathReportResident.PK;
import com.nhnacademy.springjpa.entity.Resident;
import com.nhnacademy.springjpa.exception.ReportAlreadyExistException;
import com.nhnacademy.springjpa.exception.ResidentNotFoundException;
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

  @Override
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

  @Override
  public DeathReportDTO getDeathReportData(int serialNumber) {
    DeadDTO dead = residentRepository.findDeadByResidentSerialNumber(serialNumber);
    DeathReportResidentDTO reportResident = birthDeathReportResidentRepository.findDeathReportResidentBySerialNumber(serialNumber);
    // logging start
    log.debug("getDeathReportData(): dead name -> {}", dead.getName());
    log.debug("getDeathReportData(): report name -> {}", reportResident.getName());
    // logging end
    return new DeathReportDTO(dead, reportResident);
  }

  @Override
  public void createBirthReport(int serialNumber, BirthReportPostRequest request) {
    if(!residentRepository.existsById(serialNumber) || !residentRepository.existsById(request.getReportResidentSerialNumber()))
      throw new ResidentNotFoundException("주민을 찾을 수 없습니다");

    Resident resident = residentRepository.findById(serialNumber).get();
    Resident reportResident = residentRepository.findById(request.getReportResidentSerialNumber()).get();

    BirthDeathReportResident.PK pk = new PK("출생", request.getReportResidentSerialNumber(), serialNumber);
    if(birthDeathReportResidentRepository.existsById(pk))
      throw new ReportAlreadyExistException("이미 존재하는 신고입니다");

    BirthDeathReportResident birthDeathReportResident = BirthDeathReportResident.builder()
            .pk(pk)
                .resident(resident)
                    .reportResident(reportResident)
                        .birthDeathReportDate(request.getBirthDeathReportDate())
                            .birthReportQualificationsCode(
                                request.getBirthReportQualificationsCode())
                                .emailAddress(request.getEmailAddress())
                                    .phoneNumber(request.getPhoneNumber()).build();
    birthDeathReportResidentRepository.save(birthDeathReportResident);
  }

  @Override
  public void updateBirthReport(int serialNumber, int targetSerialNumber, BirthReportPutRequest request) {
    if(!residentRepository.existsById(serialNumber) || !residentRepository.existsById(targetSerialNumber))
      throw new ResidentNotFoundException("주민을 찾을 수 없습니다");

    Resident resident = residentRepository.findById(serialNumber).get();
    Resident reportResident = residentRepository.findById(targetSerialNumber).get();

    BirthDeathReportResident.PK pk = new PK("출생", targetSerialNumber, serialNumber);
    if(!birthDeathReportResidentRepository.existsById(pk))
      throw new ReportAlreadyExistException("존재하지 않는 신고입니다");

    BirthDeathReportResident birthDeathReportResident = BirthDeathReportResident.builder()
        .pk(pk)
        .resident(resident)
        .reportResident(reportResident)
        .birthDeathReportDate(request.getBirthDeathReportDate())
        .birthReportQualificationsCode(
            request.getBirthReportQualificationsCode())
        .emailAddress(request.getEmailAddress())
        .phoneNumber(request.getPhoneNumber()).build();
    birthDeathReportResidentRepository.save(birthDeathReportResident);
  }

  @Override
  public void deleteBirthReport(int serialNumber, int targetSerialNumber) {
    if(!residentRepository.existsById(serialNumber) || !residentRepository.existsById(targetSerialNumber))
      throw new ResidentNotFoundException("주민을 찾을 수 없습니다");

    BirthDeathReportResident.PK pk = new PK("출생", targetSerialNumber, serialNumber);
    if(!birthDeathReportResidentRepository.existsById(pk))
      throw new ReportAlreadyExistException("존재하지 않는 신고입니다");

    birthDeathReportResidentRepository.deleteById(pk);
  }

  @Override
  public void createDeathReport(int serialNumber, DeathReportPostRequest request) {
    if(!residentRepository.existsById(serialNumber) || !residentRepository.existsById(request.getReportResidentSerialNumber()))
      throw new ResidentNotFoundException("주민을 찾을 수 없습니다");

    Resident resident = residentRepository.findById(serialNumber).get();
    Resident reportResident = residentRepository.findById(request.getReportResidentSerialNumber()).get();

    BirthDeathReportResident.PK pk = new PK("사망", request.getReportResidentSerialNumber(), serialNumber);
    if(birthDeathReportResidentRepository.existsById(pk))
      throw new ReportAlreadyExistException("이미 존재하는 신고입니다");

    BirthDeathReportResident birthDeathReportResident = BirthDeathReportResident.builder()
        .pk(pk)
        .resident(resident)
        .reportResident(reportResident)
        .birthDeathReportDate(request.getBirthDeathReportDate())
        .deathReportQualificationsCode(
            request.getDeathReportQualificationsCode())
        .emailAddress(request.getEmailAddress())
        .phoneNumber(request.getPhoneNumber()).build();
    birthDeathReportResidentRepository.save(birthDeathReportResident);
  }

  @Override
  public void updateDeathReport(int serialNumber, int targetSerialNumber,
      DeathReportPutRequest request) {
    if(!residentRepository.existsById(serialNumber) || !residentRepository.existsById(targetSerialNumber))
      throw new ResidentNotFoundException("주민을 찾을 수 없습니다");

    Resident resident = residentRepository.findById(serialNumber).get();
    Resident reportResident = residentRepository.findById(targetSerialNumber).get();

    BirthDeathReportResident.PK pk = new PK("사망", targetSerialNumber, serialNumber);
    if(!birthDeathReportResidentRepository.existsById(pk))
      throw new ReportAlreadyExistException("존재하지 않는 신고입니다");

    BirthDeathReportResident birthDeathReportResident = BirthDeathReportResident.builder()
        .pk(pk)
        .resident(resident)
        .reportResident(reportResident)
        .birthDeathReportDate(request.getBirthDeathReportDate())
        .deathReportQualificationsCode(
            request.getDeathReportQualificationsCode())
        .emailAddress(request.getEmailAddress())
        .phoneNumber(request.getPhoneNumber()).build();
    birthDeathReportResidentRepository.save(birthDeathReportResident);

  }

  @Override
  public void deleteDeathReport(int serialNumber, int targetSerialNumber) {
    if(!residentRepository.existsById(serialNumber) || !residentRepository.existsById(targetSerialNumber))
      throw new ResidentNotFoundException("주민을 찾을 수 없습니다");

    BirthDeathReportResident.PK pk = new PK("사망", targetSerialNumber, serialNumber);
    if(!birthDeathReportResidentRepository.existsById(pk))
      throw new ReportAlreadyExistException("존재하지 않는 신고입니다");

    birthDeathReportResidentRepository.deleteById(pk);
  }
}
