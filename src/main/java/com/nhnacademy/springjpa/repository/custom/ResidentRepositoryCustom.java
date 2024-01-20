package com.nhnacademy.springjpa.repository.custom;

import com.nhnacademy.springjpa.domain.dto.resident.IssuableResidentDTO;
import java.util.List;

public interface ResidentRepositoryCustom {
  List<IssuableResidentDTO> findResidentInfoWithBirthDeathAndCertificate();

}
