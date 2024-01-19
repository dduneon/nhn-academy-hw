package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.domain.ResidentDTO;
import java.util.List;
import org.springframework.data.domain.Page;

public interface ResidentRepositoryCustom {
  List<ResidentDTO> findResidentInfoWithBirthDeathAndCertificate();

}
