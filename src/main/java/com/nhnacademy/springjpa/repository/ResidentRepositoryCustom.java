package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.domain.IssuableResidentDTO;
import java.util.List;

public interface ResidentRepositoryCustom {
  List<IssuableResidentDTO> findResidentInfoWithBirthDeathAndCertificate();

}
