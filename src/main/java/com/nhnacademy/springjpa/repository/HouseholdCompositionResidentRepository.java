package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.domain.dto.residentregister.HouseholdCompositionResidentDTO;
import com.nhnacademy.springjpa.entity.HouseholdCompositionResident;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HouseholdCompositionResidentRepository extends JpaRepository<HouseholdCompositionResident, HouseholdCompositionResident.PK> {
  HouseholdCompositionResident findByPk_ResidentSerialNumber(int residentSerialNumber);

  @Query("select new com.nhnacademy.springjpa.domain.dto.residentregister.HouseholdCompositionResidentDTO(hcr.householdRelationshipCode, r.name, r.residentRegistrationNumber, hcr.reportDate, hcr.householdCompositionChangeReasonCode)"
      + " from HouseholdCompositionResident hcr"
      + " left join Resident r"
      + " on hcr.pk.residentSerialNumber = r.residentSerialNumber"
      + " where hcr.pk.householdSerialNumber = ?1")
  List<HouseholdCompositionResidentDTO> findCompositionResidentsByHouseholdSerialNumber(int householdSerialNumber);

}
