package com.nhnacademy.springjpa.service.Impl;

import com.nhnacademy.springjpa.domain.request.HouseholdMovementPostRequest;
import com.nhnacademy.springjpa.domain.request.HouseholdMovementPutRequest;
import com.nhnacademy.springjpa.entity.Household;
import com.nhnacademy.springjpa.entity.HouseholdMovementAddress;
import com.nhnacademy.springjpa.entity.HouseholdMovementAddress.PK;
import com.nhnacademy.springjpa.exception.HouseholdMovementAddressAlreadyExistException;
import com.nhnacademy.springjpa.exception.HouseholdMovementAddressNotFoundException;
import com.nhnacademy.springjpa.exception.HouseholdNotFoundException;
import com.nhnacademy.springjpa.repository.HouseholdMovementAddressRepository;
import com.nhnacademy.springjpa.repository.HouseholdRepository;
import com.nhnacademy.springjpa.service.HouseholdMovementService;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;

@Service
public class HouseholdMovementServiceImpl implements HouseholdMovementService {
  private final HouseholdMovementAddressRepository householdMovementAddressRepository;
  private final HouseholdRepository householdRepository;

  public HouseholdMovementServiceImpl(
      HouseholdMovementAddressRepository householdMovementAddressRepository,
      HouseholdRepository householdRepository) {
    this.householdMovementAddressRepository = householdMovementAddressRepository;
    this.householdRepository = householdRepository;
  }

  public void createHouseholdMovement(int householdSerialNumber, HouseholdMovementPostRequest request) {
    if(!householdRepository.existsById(householdSerialNumber))
      throw new HouseholdNotFoundException("존재하지 않는 세대입니다");

    HouseholdMovementAddress.PK pk = new PK(request.getHouseMovementReportDate(), householdSerialNumber);
    if(householdMovementAddressRepository.existsById(pk))
      throw new HouseholdMovementAddressAlreadyExistException("이미 존재하는 세대 전입 정보입니다");

    Household household = householdRepository.findById(householdSerialNumber).get();
    householdMovementAddressRepository.save(HouseholdMovementAddress.builder()
        .pk(pk)
            .household(household)
            .houseMovementAddress(request.getHouseMovementAddress())
            .lastAddressYn("N")
        .build());

    List<HouseholdMovementAddress> all = householdMovementAddressRepository.findByPkHouseholdSerialNumberAndLastAddressYn(householdSerialNumber, "Y");
    for(HouseholdMovementAddress hma: all) {
      hma.setLastAddressYn("N");
      householdMovementAddressRepository.save(hma);
    }

    HouseholdMovementAddress last = householdMovementAddressRepository.findFirstByPkHouseholdSerialNumberOrderByPkHouseMovementReportDateDesc(householdSerialNumber);
    last.setLastAddressYn("Y");
    householdMovementAddressRepository.save(last);
  }

  public void updateHouseholdMovement(int householdSerialNumber, LocalDate reportDate, HouseholdMovementPutRequest request) {
    if(!householdRepository.existsById(householdSerialNumber))
      throw new HouseholdNotFoundException("존재하지 않는 세대입니다");

    HouseholdMovementAddress.PK pk = new PK(reportDate, householdSerialNumber);
    if(!householdMovementAddressRepository.existsById(pk))
      throw new HouseholdMovementAddressNotFoundException("존재하지 않는 전입 정보입니다");

    Household household = householdRepository.findById(householdSerialNumber).get();

    householdMovementAddressRepository.save(HouseholdMovementAddress.builder()
        .pk(pk)
        .household(household)
        .houseMovementAddress(request.getHouseMovementAddress())
        .lastAddressYn("N")
        .build());
  }

  public void deleteHouseholdMovement(int householdSerialNumber, LocalDate reportDate) {
    if(!householdRepository.existsById(householdSerialNumber))
      throw new HouseholdNotFoundException("존재하지 않는 세대입니다");

    HouseholdMovementAddress.PK pk = new PK(reportDate, householdSerialNumber);
    if(!householdMovementAddressRepository.existsById(pk))
      throw new HouseholdMovementAddressNotFoundException("존재하지 않는 전입 정보입니다");

    householdMovementAddressRepository.deleteById(pk);

    List<HouseholdMovementAddress> all = householdMovementAddressRepository.findByPkHouseholdSerialNumberAndLastAddressYn(householdSerialNumber, "Y");
    for(HouseholdMovementAddress hma: all) {
      hma.setLastAddressYn("N");
      householdMovementAddressRepository.save(hma);
    }

    HouseholdMovementAddress last = householdMovementAddressRepository.findFirstByPkHouseholdSerialNumberOrderByPkHouseMovementReportDateDesc(householdSerialNumber);
    last.setLastAddressYn("Y");
  }

}
