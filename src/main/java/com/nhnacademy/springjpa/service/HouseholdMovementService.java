package com.nhnacademy.springjpa.service;

import com.nhnacademy.springjpa.domain.request.HouseholdMovementPostRequest;
import com.nhnacademy.springjpa.domain.request.HouseholdMovementPutRequest;
import java.time.LocalDate;

public interface HouseholdMovementService {
  void createHouseholdMovement(int householdSerialNumber, HouseholdMovementPostRequest request);
  void updateHouseholdMovement(int householdSerialNumber, LocalDate reportDate, HouseholdMovementPutRequest request);

  void deleteHouseholdMovement(int householdSerialNumber, LocalDate reportDate);
}
