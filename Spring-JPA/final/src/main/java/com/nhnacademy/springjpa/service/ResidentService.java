package com.nhnacademy.springjpa.service;

import com.nhnacademy.springjpa.domain.dto.resident.IssuableResidentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ResidentService {
  Page<IssuableResidentDTO> getPagedResidentList(Pageable pageable);

  int getResidentCount();

}
