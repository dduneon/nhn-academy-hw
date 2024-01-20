package com.nhnacademy.springjpa.service;

import com.nhnacademy.springjpa.domain.dto.residentregister.ResidentRegisterDTO;

public interface IssueCertificateService {
  ResidentRegisterDTO getResidentRegisterData(int serialNumber);
}
