package com.nhnacademy.springjpa.service.Impl;

import com.nhnacademy.springjpa.domain.dto.resident.IssuableResidentDTO;
import com.nhnacademy.springjpa.repository.ResidentRepository;
import com.nhnacademy.springjpa.service.ResidentService;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ResidentServiceImpl implements ResidentService {
  // todo 이름 뭘로 바꿀지?
  private ResidentRepository residentRepository;

  public ResidentServiceImpl(ResidentRepository residentRepository) {
    this.residentRepository = residentRepository;
  }

  public Page<IssuableResidentDTO> getPagedResidentList(Pageable pageable) {
    List<IssuableResidentDTO> residentList =  residentRepository.findResidentInfoWithBirthDeathAndCertificate();
    PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
    int start = (int) pageRequest.getOffset();
    int end = Math.min((start + pageable.getPageSize()), residentList.size());

    return new PageImpl<>(residentList.subList(start, end), pageRequest, residentList.size());
  }

  public int getResidentCount() {
    return (int) residentRepository.count();
  }


}
