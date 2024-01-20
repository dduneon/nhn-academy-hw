package com.nhnacademy.springjpa.service.Impl;

import com.nhnacademy.springjpa.domain.dto.residentregister.HouseMovementAddressLogDTO;
import com.nhnacademy.springjpa.domain.dto.residentregister.HouseholdCompositionResidentDTO;
import com.nhnacademy.springjpa.domain.dto.residentregister.HouseholdResidentInfoDTO;
import com.nhnacademy.springjpa.domain.dto.residentregister.ResidentRegisterDTO;
import com.nhnacademy.springjpa.entity.HouseholdCompositionResident;
import com.nhnacademy.springjpa.repository.HouseholdCompositionResidentRepository;
import com.nhnacademy.springjpa.repository.HouseholdMovementAddressRepository;
import com.nhnacademy.springjpa.repository.HouseholdRepository;
import com.nhnacademy.springjpa.service.IssueCertificateService;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class IssueCertificateServiceImpl implements IssueCertificateService {
  private final HouseholdCompositionResidentRepository householdCompositionResidentRepository;
  private final HouseholdRepository householdRepository;
  private final HouseholdMovementAddressRepository householdMovementAddressRepository;

  public IssueCertificateServiceImpl(
      HouseholdCompositionResidentRepository householdCompositionResidentRepository,
      HouseholdRepository householdRepository,
      HouseholdMovementAddressRepository householdMovementAddressRepository) {
    this.householdCompositionResidentRepository = householdCompositionResidentRepository;
    this.householdRepository = householdRepository;
    this.householdMovementAddressRepository = householdMovementAddressRepository;
  }

  public ResidentRegisterDTO getResidentRegisterData(int serialNumber) {
    int householdSerialNumber = householdCompositionResidentRepository.findByPk_ResidentSerialNumber(serialNumber).getPk().getHouseholdSerialNumber();
    log.debug("getResidentRegisterData(): householdSerialNumber -> {}", householdSerialNumber);
    HouseholdResidentInfoDTO householdResidentInfo = householdRepository.findHouseholdResidentByHouseholdResidentSerialNumber(householdSerialNumber);
    log.debug("getResidentRegisterData(): householdResidentInfo -> {}", householdResidentInfo.getName());
    List<HouseMovementAddressLogDTO> houseMovementAddressLog = householdMovementAddressRepository.findAddressesByPkHouseholdSerialNumber(householdSerialNumber);
    for(HouseMovementAddressLogDTO h: houseMovementAddressLog) {
      log.debug("getResidentRegisterData(): movementAddressLogDto -> {}", h.getHouseMovementAddress());
    }
    List<HouseholdCompositionResidentDTO> householdCompositionResident = householdCompositionResidentRepository.findCompositionResidentsByHouseholdSerialNumber(householdSerialNumber);

    return new ResidentRegisterDTO(householdResidentInfo, houseMovementAddressLog, householdCompositionResident);
  }
}
