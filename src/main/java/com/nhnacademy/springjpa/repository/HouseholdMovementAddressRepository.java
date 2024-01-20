package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.domain.dto.residentregister.HouseMovementAddressLogDTO;
import com.nhnacademy.springjpa.entity.HouseholdMovementAddress;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HouseholdMovementAddressRepository extends JpaRepository<HouseholdMovementAddress, HouseholdMovementAddress.PK> {
  List<HouseMovementAddressLogDTO> findAddressesByPkHouseholdSerialNumber(int householdSerialNumber);
  //todo order by
}
