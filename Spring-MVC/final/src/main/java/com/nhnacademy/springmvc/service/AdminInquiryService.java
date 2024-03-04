package com.nhnacademy.springmvc.service;

import com.nhnacademy.springmvc.domain.Answer;
import com.nhnacademy.springmvc.domain.Inquiry;
import java.util.List;

public interface AdminInquiryService {
  List<Inquiry> getNotRespondInquiries();
  Inquiry getInquiryById(long inquiryId);
  void modifyAnsweredStatus(long inquiryId, String author);
  }
