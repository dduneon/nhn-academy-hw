package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.domain.dto.birth.BirthParentsDTO;
import com.nhnacademy.springjpa.entity.FamilyRelationship;
import com.nhnacademy.springjpa.repository.custom.FamilyRelationshipRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FamilyRelationshipRepository extends FamilyRelationshipRepositoryCustom, JpaRepository<FamilyRelationship, FamilyRelationship.PK> {
  @Query("select new com.nhnacademy.springjpa.domain.dto.birth.BirthParentsDTO(r.name, r.residentRegistrationNumber)"
      + " from FamilyRelationship fr"
    + " left join Resident r"
  + " on fr.pk.familyResidentSerialNumber = r.residentSerialNumber"
  + " where fr.pk.baseResidentSerialNumber = :serialNumber and fr.familyRelationshipCode = :code")
  BirthParentsDTO findRelationParentsBySerialNumber(@Param("serialNumber") int serialNumber, @Param("code") String code);
}
