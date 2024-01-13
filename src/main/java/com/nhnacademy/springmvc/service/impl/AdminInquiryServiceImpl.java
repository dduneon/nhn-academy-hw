package com.nhnacademy.springmvc.service.impl;

import com.nhnacademy.springmvc.domain.Inquiry;
import com.nhnacademy.springmvc.repository.InquiryRepository;
import com.nhnacademy.springmvc.service.AdminInquiryService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

@Repository
public class AdminInquiryServiceImpl implements AdminInquiryService {
  private final InquiryRepository inquiryRepository;

  public AdminInquiryServiceImpl(InquiryRepository inquiryRepository) {
    this.inquiryRepository = inquiryRepository;
  }

  @Override
  public List<Inquiry> getNotRespondInquiries() {
    return inquiryRepository.findAll()
        .stream()
        .filter(inquiry -> !inquiry.isRespond())
        .collect(Collectors.toList());
  }
}
