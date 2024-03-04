package com.nhnacademy.springjpa.service.Impl;

import com.nhnacademy.springjpa.domain.dto.certlist.CertificationIssueDTO;
import com.nhnacademy.springjpa.domain.dto.certlist.CertificationIssueInfoDTO;
import com.nhnacademy.springjpa.domain.dto.certlist.ResidentDTO;
import com.nhnacademy.springjpa.domain.dto.familyrelationship.BaseResidentDTO;
import com.nhnacademy.springjpa.domain.dto.familyrelationship.FamilyRelationshipCertificateDTO;
import com.nhnacademy.springjpa.domain.dto.familyrelationship.FamilyResidentDTO;
import com.nhnacademy.springjpa.domain.dto.residentregister.CertificateIssueDTO;
import com.nhnacademy.springjpa.domain.dto.residentregister.HouseMovementAddressLogDTO;
import com.nhnacademy.springjpa.domain.dto.residentregister.HouseholdCompositionResidentDTO;
import com.nhnacademy.springjpa.domain.dto.residentregister.HouseholdResidentInfoDTO;
import com.nhnacademy.springjpa.domain.dto.residentregister.ResidentRegisterCertificateDTO;
import com.nhnacademy.springjpa.entity.CertificateIssue;
import com.nhnacademy.springjpa.entity.Resident;
import com.nhnacademy.springjpa.repository.CertificateIssueRepository;
import com.nhnacademy.springjpa.repository.FamilyRelationshipRepository;
import com.nhnacademy.springjpa.repository.HouseholdCompositionResidentRepository;
import com.nhnacademy.springjpa.repository.HouseholdMovementAddressRepository;
import com.nhnacademy.springjpa.repository.HouseholdRepository;
import com.nhnacademy.springjpa.repository.ResidentRepository;
import com.nhnacademy.springjpa.service.IssueCertificateService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class IssueCertificateServiceImpl implements IssueCertificateService {
  private final HouseholdCompositionResidentRepository householdCompositionResidentRepository;
  private final HouseholdRepository householdRepository;
  private final HouseholdMovementAddressRepository householdMovementAddressRepository;
  private final CertificateIssueRepository certificateIssueRepository;
  private final ResidentRepository residentRepository;
  private final FamilyRelationshipRepository familyRelationshipRepository;


  public IssueCertificateServiceImpl(
      HouseholdCompositionResidentRepository householdCompositionResidentRepository,
      HouseholdRepository householdRepository,
      HouseholdMovementAddressRepository householdMovementAddressRepository,
      CertificateIssueRepository certificateIssueRepository, ResidentRepository residentRepository,
      FamilyRelationshipRepository familyRelationshipRepository) {
    this.householdCompositionResidentRepository = householdCompositionResidentRepository;
    this.householdRepository = householdRepository;
    this.householdMovementAddressRepository = householdMovementAddressRepository;
    this.certificateIssueRepository = certificateIssueRepository;
    this.residentRepository = residentRepository;
    this.familyRelationshipRepository = familyRelationshipRepository;
  }

  public ResidentRegisterCertificateDTO getResidentRegisterData(int serialNumber) {
    Resident resident = residentRepository.findById(serialNumber).get();
    CertificateIssue certificateIssue = new CertificateIssue(resident, "주민등록등본");
    certificateIssueRepository.save(certificateIssue);
    CertificateIssueDTO certificateIssueDTO = new CertificateIssueDTO(certificateIssue.getCertificateIssueDate(), certificateIssue.getCertificateConfirmationNumber());
    log.debug("getResidentRegisterData(): date -> {}", certificateIssueDTO.getCertificateIssueDate());


    int householdSerialNumber = householdCompositionResidentRepository.findByPk_ResidentSerialNumber(serialNumber).getPk().getHouseholdSerialNumber();
    log.debug("getResidentRegisterData(): householdSerialNumber -> {}", householdSerialNumber);
    HouseholdResidentInfoDTO householdResidentInfo = householdRepository.findHouseholdResidentByHouseholdResidentSerialNumber(householdSerialNumber);
    log.debug("getResidentRegisterData(): householdResidentInfo -> {}", householdResidentInfo.getName());
    List<HouseMovementAddressLogDTO> houseMovementAddressLog = householdMovementAddressRepository.findAddressesByPkHouseholdSerialNumber(householdSerialNumber);
    for(HouseMovementAddressLogDTO h: houseMovementAddressLog) {
      log.debug("getResidentRegisterData(): movementAddressLogDto -> {}", h.getHouseMovementAddress());
    }
    List<HouseholdCompositionResidentDTO> householdCompositionResident = householdCompositionResidentRepository.findCompositionResidentsByHouseholdSerialNumber(householdSerialNumber);
    for(HouseholdCompositionResidentDTO h: householdCompositionResident) {
      log.debug("getResidentRegisterData(): householdCompositionResident -> {}", h.getName());
    }

    return new ResidentRegisterCertificateDTO(certificateIssueDTO, householdResidentInfo, houseMovementAddressLog, householdCompositionResident);
  }

  public FamilyRelationshipCertificateDTO getFamilyRelationshipData(int serialNumber) {
    Resident resident = residentRepository.findById(serialNumber).get();
    CertificateIssue certificateIssue = new CertificateIssue(resident, "가족관계증명서");
    certificateIssueRepository.save(certificateIssue);
    CertificateIssueDTO certificateIssueDTO = new CertificateIssueDTO(certificateIssue.getCertificateIssueDate(), certificateIssue.getCertificateConfirmationNumber());
    log.debug("getFamilyRelationshipData(): date -> {}", certificateIssueDTO.getCertificateIssueDate());

    // 본인
    BaseResidentDTO baseResident = residentRepository.findBaseResidentByResidentSerialNumber(serialNumber);
    log.debug("getFamilyRelationshipData(): baseResident -> {}", baseResident.getName());
    // 가족들
    List<FamilyResidentDTO> familyResidents = familyRelationshipRepository.getRelationalFamilyResident(serialNumber);
    for(FamilyResidentDTO f: familyResidents) {
      log.debug("getFamilyRelationshipData(): family -> {}", f.getFamilyRelationshipCode());
    }

    return new FamilyRelationshipCertificateDTO(certificateIssueDTO, baseResident, familyResidents);
  }

  public CertificationIssueDTO getCertificationList(int serialNumber, Pageable pageable) {
    ResidentDTO resident = residentRepository.findResidentByResidentSerialNumber(serialNumber);
    List<CertificationIssueInfoDTO> certList = certificateIssueRepository.findByResident_ResidentSerialNumber(serialNumber);
    PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
    int start = (int) pageRequest.getOffset();
    int end = Math.min((start + pageable.getPageSize()), certList.size());
    Page<CertificationIssueInfoDTO> certPageList = new PageImpl<>(certList.subList(start, end), pageRequest, certList.size());

    return new CertificationIssueDTO(resident, certPageList);
  }

  public int getCertificationListCount(int serialNumber) {
    return certificateIssueRepository.countByResident_ResidentSerialNumber(serialNumber);
  }
}
