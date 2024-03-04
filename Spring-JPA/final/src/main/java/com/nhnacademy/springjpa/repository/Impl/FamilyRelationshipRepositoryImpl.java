package com.nhnacademy.springjpa.repository.Impl;

import com.nhnacademy.springjpa.domain.dto.familyrelationship.FamilyRelationshipCertificateDTO;
import com.nhnacademy.springjpa.domain.dto.familyrelationship.FamilyResidentDTO;
import com.nhnacademy.springjpa.entity.QFamilyRelationship;
import com.nhnacademy.springjpa.entity.QResident;
import com.nhnacademy.springjpa.repository.FamilyRelationshipRepository;
import com.nhnacademy.springjpa.repository.custom.FamilyRelationshipRepositoryCustom;
import com.querydsl.core.types.Projections;
import java.util.List;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class FamilyRelationshipRepositoryImpl extends QuerydslRepositorySupport implements
    FamilyRelationshipRepositoryCustom {

  public FamilyRelationshipRepositoryImpl() {
    super(FamilyRelationshipRepository.class);
  }

  public List<FamilyResidentDTO> getRelationalFamilyResident(int baseResidentSerialNumber) {
    QFamilyRelationship familyRelationship = QFamilyRelationship.familyRelationship;
    QResident resident = QResident.resident;

    return from(familyRelationship)
        .leftJoin(familyRelationship.familyResident, resident)
        .where(familyRelationship.baseResident.residentSerialNumber.eq(baseResidentSerialNumber))
        .orderBy(resident.birthDate.asc())
        .select(Projections.constructor(FamilyResidentDTO.class,
            familyRelationship.familyRelationshipCode,
            resident.name,
            resident.birthDate,
            resident.residentRegistrationNumber,
            resident.genderCode))
        .fetch();
  }
}
