package com.nhnacademy.springmvc.service.impl;

import com.nhnacademy.springmvc.domain.Answer;
import com.nhnacademy.springmvc.domain.Inquiry;
import com.nhnacademy.springmvc.exception.NotModifiedAnswerStatusException;
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
    return inquiryRepository.findAll().values()
        .stream()
        .filter(inquiry -> !inquiry.isAnswered())
        .sorted((inq1, inq2) -> Math.toIntExact(inq2.getId() - inq1.getId()))
        .collect(Collectors.toList());
  }

  @Override
  public Inquiry getInquiryById(long inquiryId) {
    return inquiryRepository.findAll().get(inquiryId);
  }

  @Override
  public void modifyAnsweredStatus(long inquiryId, String author) {
    if(!inquiryRepository.updateAnsweredStatus(inquiryId, author)) {
      throw new NotModifiedAnswerStatusException();
    }
  }
}
