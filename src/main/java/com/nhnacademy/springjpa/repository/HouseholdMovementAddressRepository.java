package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.domain.dto.residentregister.HouseMovementAddressLogDTO;
import com.nhnacademy.springjpa.entity.HouseholdMovementAddress;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HouseholdMovementAddressRepository extends JpaRepository<HouseholdMovementAddress, HouseholdMovementAddress.PK> {
  @Query("select new com.nhnacademy.springjpa.domain.dto.residentregister.HouseMovementAddressLogDTO(hma.houseMovementAddress, hma.pk.houseMovementReportDate)"
      + " from HouseholdMovementAddress hma"
      + " where hma.pk.householdSerialNumber = ?1"
      + " order by hma.pk.houseMovementReportDate desc ")
  List<HouseMovementAddressLogDTO> findAddressesByPkHouseholdSerialNumber(int householdSerialNumber);
}
