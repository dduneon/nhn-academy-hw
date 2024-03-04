package com.nhnacademy.springjpa.service.Impl;

import com.nhnacademy.springjpa.domain.request.ResidentPostRequest;
import com.nhnacademy.springjpa.domain.request.ResidentPutRequest;
import com.nhnacademy.springjpa.entity.Resident;
import com.nhnacademy.springjpa.exception.ResidentAlreadyExistException;
import com.nhnacademy.springjpa.exception.ResidentDeleteFailedException;
import com.nhnacademy.springjpa.exception.ResidentNotFoundException;
import com.nhnacademy.springjpa.repository.HouseholdRepository;
import com.nhnacademy.springjpa.repository.ResidentRepository;
import com.nhnacademy.springjpa.service.ResidentManageService;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ResidentManageServiceImpl implements ResidentManageService {
  private final HouseholdRepository householdRepository;
  private final ResidentRepository residentRepository;

  public ResidentManageServiceImpl(HouseholdRepository householdRepository,
      ResidentRepository residentRepository) {
    this.householdRepository = householdRepository;
    this.residentRepository = residentRepository;
  }

  public void deleteResident(int residentSerialNumber) {
    Integer householdResidentSerialNumber = householdRepository.findHouseholdResidentSerialNumberByResidentSerialNumber(residentSerialNumber);
    if(Objects.isNull(householdResidentSerialNumber) || householdResidentSerialNumber != residentSerialNumber) {
      residentRepository.deleteById(residentSerialNumber);
      return;
    }
    // 세대가 없는 사람, 세대주가 아닌 사람은 지워도 됨
    int residentCount = householdRepository.countByHouseholdResidentSerialNumber(householdResidentSerialNumber);
    if(residentCount <= 1) {
      residentRepository.deleteById(residentSerialNumber);
      return;
    }
    // 세대 구성원이 1명 이하인 경우만 삭제 가능
    throw new ResidentDeleteFailedException("남은 가족이 있어 삭제가 불가능합니다");
  }

  public void saveResident(ResidentPostRequest request) {
    if(residentRepository.existsById(request.getResidentSerialNumber())){
      throw new ResidentAlreadyExistException("이미 존재하는 Serial Number 입니다");
    }
    Resident resident = Resident.builder()
        .residentSerialNumber(request.getResidentSerialNumber())
        .name(request.getName())
        .residentRegistrationNumber(request.getResidentRegistrationNumber())
        .genderCode(request.getGenderCode())
        .birthDate(request.getBirthDate())
        .birthPlaceCode(request.getBirthPlaceCode())
        .registrationBaseAddress(request.getRegistrationBaseAddress())
        .deathDate(request.getDeathDate())
        .deathPlaceAddress(request.getDeathPlaceAddress())
        .deathPlaceAddress(request.getDeathPlaceAddress()).build();

    residentRepository.save(resident);
  }

  public void updateResident(int serialNumber, ResidentPutRequest request) {
    if(!residentRepository.existsById(serialNumber)){
      throw new ResidentNotFoundException("주민을 찾을 수 없습니다");
    }
    Resident resident = Resident.builder()
        .residentSerialNumber(serialNumber)
        .name(request.getName())
        .residentRegistrationNumber(request.getResidentRegistrationNumber())
        .genderCode(request.getGenderCode())
        .birthDate(request.getBirthDate())
        .birthPlaceCode(request.getBirthPlaceCode())
        .registrationBaseAddress(request.getRegistrationBaseAddress())
        .deathDate(request.getDeathDate())
        .deathPlaceAddress(request.getDeathPlaceAddress())
        .deathPlaceAddress(request.getDeathPlaceAddress()).build();

    residentRepository.save(resident);
  }
}
