package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.domain.dto.residentregister.HouseholdResidentInfoDTO;
import com.nhnacademy.springjpa.entity.Household;
import com.nhnacademy.springjpa.repository.custom.HouseholdRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HouseholdRepository extends HouseholdRepositoryCustom, JpaRepository<Household, Integer> {
  @Query("select new com.nhnacademy.springjpa.domain.dto.residentregister.HouseholdResidentInfoDTO(r.name, h.householdCompositionReasonCode, h.householdCompositionDate)"
      + " from Household h"
      + " left join Resident r"
      + " on h.resident.residentSerialNumber = r.residentSerialNumber"
      + " where h.householdSerialNumber = ?1")
  HouseholdResidentInfoDTO findHouseholdResidentByHouseholdResidentSerialNumber(int houseHoldSerialNumber);

  @Query("select h.resident.residentSerialNumber"
      + " from Household h"
      + " right join HouseholdCompositionResident hcr"
      + " on h.householdSerialNumber = hcr.pk.householdSerialNumber"
      + " where hcr.pk.residentSerialNumber = ?1")
  Integer findHouseholdResidentSerialNumberByResidentSerialNumber(int residentSerialNumber);
}
