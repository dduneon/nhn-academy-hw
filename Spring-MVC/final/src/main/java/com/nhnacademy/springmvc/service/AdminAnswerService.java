package com.nhnacademy.springmvc.service;

import com.nhnacademy.springmvc.domain.Answer;

public interface AdminAnswerService {
  void addInquiryAnswer(long inquiryId, Answer answer);
}
