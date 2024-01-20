package com.nhnacademy.springjpa.service;

import com.nhnacademy.springjpa.domain.IssuableResidentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ResidentService {
  Page<IssuableResidentDTO> getMainResidentData(Pageable pageable);

  int getResidentCount();

}
