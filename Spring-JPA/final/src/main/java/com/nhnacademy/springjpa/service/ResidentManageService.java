package com.nhnacademy.springjpa.service;

import com.nhnacademy.springjpa.domain.request.ResidentPostRequest;
import com.nhnacademy.springjpa.domain.request.ResidentPutRequest;

public interface ResidentManageService {
  void deleteResident(int residentSerialNumber);
  void saveResident(ResidentPostRequest request);
  void updateResident(int serialNumber, ResidentPutRequest request);

}
