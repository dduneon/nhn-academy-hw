package com.nhnacademy.springjpa.repository.Impl;

import com.nhnacademy.springjpa.entity.Household;
import com.nhnacademy.springjpa.entity.QHousehold;
import com.nhnacademy.springjpa.entity.QHouseholdCompositionResident;
import com.nhnacademy.springjpa.repository.custom.HouseholdRepositoryCustom;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class HouseholdRepositoryImpl extends QuerydslRepositorySupport implements
    HouseholdRepositoryCustom {

  public HouseholdRepositoryImpl() {
    super(Household.class);
  }

  public int countByHouseholdResidentSerialNumber(int householdResidentSerialNumber) {
    QHousehold household = QHousehold.household;
    QHouseholdCompositionResident householdCompositionResident = QHouseholdCompositionResident.householdCompositionResident;

    return (int) from(householdCompositionResident)
        .rightJoin(householdCompositionResident.household, household)
        .where(household.resident.residentSerialNumber.eq(householdResidentSerialNumber))
        .fetchCount();
  }
}
