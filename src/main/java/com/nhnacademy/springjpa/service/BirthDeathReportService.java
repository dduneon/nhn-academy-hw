package com.nhnacademy.springjpa.service;

import com.nhnacademy.springjpa.domain.dto.birth.BirthReportDTO;
import com.nhnacademy.springjpa.domain.dto.death.DeathReportDTO;

public interface BirthDeathReportService {
  BirthReportDTO getBirthReportData(int serialNumber);
  DeathReportDTO getDeathReportData(int serialNumber);

}
