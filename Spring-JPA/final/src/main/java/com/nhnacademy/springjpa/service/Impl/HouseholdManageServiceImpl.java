package com.nhnacademy.springjpa.service.Impl;

import com.nhnacademy.springjpa.domain.request.HouseholdPostRequest;
import com.nhnacademy.springjpa.entity.Household;
import com.nhnacademy.springjpa.entity.Resident;
import com.nhnacademy.springjpa.exception.HouseholdAlreadyExistException;
import com.nhnacademy.springjpa.exception.HouseholdNotFoundException;
import com.nhnacademy.springjpa.exception.ResidentNotFoundException;
import com.nhnacademy.springjpa.repository.HouseholdRepository;
import com.nhnacademy.springjpa.repository.ResidentRepository;
import com.nhnacademy.springjpa.service.HouseholdManageService;
import org.springframework.stereotype.Service;

@Service
public class HouseholdManageServiceImpl implements HouseholdManageService {
  private final HouseholdRepository householdRepository;
  private final ResidentRepository residentRepository;

  public HouseholdManageServiceImpl(HouseholdRepository householdRepository,
      ResidentRepository residentRepository) {
    this.householdRepository = householdRepository;
    this.residentRepository = residentRepository;
  }

  public void createHousehold(HouseholdPostRequest request) {
    if(!residentRepository.existsById(request.getHouseholdResidentSerialNumber()))
      throw new ResidentNotFoundException("주민을 찾을 수 없습니다");
    if(householdRepository.existsById(request.getHouseholdSerialNumber()))
      throw new HouseholdAlreadyExistException("이미 존재하는 세대입니다");

    Resident resident = residentRepository.findById(request.getHouseholdResidentSerialNumber()).get();
    householdRepository.save(Household.builder()
            .householdSerialNumber(request.getHouseholdSerialNumber())
            .resident(resident)
            .householdCompositionDate(request.getHouseholdCompositionDate())
            .householdCompositionReasonCode(request.getHouseholdCompositionReasonCode())
            .currentHouseMovementAddress(request.getCurrentHouseMovementAddress())
        .build());
  }

  public void deleteHousehold(int householdSerialNumber) {
    if(!householdRepository.existsById(householdSerialNumber))
      throw new HouseholdNotFoundException("삭제할 세대가 존재하지 않습니다");
    householdRepository.deleteById(householdSerialNumber);
  }

}
