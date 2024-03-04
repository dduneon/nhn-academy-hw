package com.nhnacademy.springjpa.service;

import com.nhnacademy.springjpa.domain.dto.birth.BirthReportDTO;
import com.nhnacademy.springjpa.domain.dto.death.DeathReportDTO;
import com.nhnacademy.springjpa.domain.request.BirthReportPostRequest;
import com.nhnacademy.springjpa.domain.request.BirthReportPutRequest;
import com.nhnacademy.springjpa.domain.request.DeathReportPostRequest;
import com.nhnacademy.springjpa.domain.request.DeathReportPutRequest;

public interface BirthDeathReportService {
  BirthReportDTO getBirthReportData(int serialNumber);
  DeathReportDTO getDeathReportData(int serialNumber);

  void createBirthReport(int serialNumber, BirthReportPostRequest request);
  void updateBirthReport(int serialNumber, int targetSerialNumber, BirthReportPutRequest request);
  void deleteBirthReport(int serialNumber, int targetSerialNumber);

  void createDeathReport(int serialNumber, DeathReportPostRequest request);
  void updateDeathReport(int serialNumber, int targetSerialNumber, DeathReportPutRequest request);
  void deleteDeathReport(int serialNumber, int targetSerialNumber);
}
