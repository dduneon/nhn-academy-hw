package com.nhnacademy.springjpa.service;

import com.nhnacademy.springjpa.domain.request.HouseholdPostRequest;

public interface HouseholdManageService {
  void createHousehold(HouseholdPostRequest request);
  void deleteHousehold(int householdSerialNumber);

}
