package com.nhnacademy.springjpa.service;

import com.nhnacademy.springjpa.domain.ResidentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ResidentService {
  Page<ResidentDTO> getMainResidentData(Pageable pageable);

  int getResidentCount();

}
