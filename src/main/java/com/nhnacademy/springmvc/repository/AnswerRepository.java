package com.nhnacademy.springmvc.repository;

import com.nhnacademy.springmvc.domain.Answer;

public interface AnswerRepository {
  void save(Long inquiryId, Answer answer);

  Answer findByInquiryId(Long inquiryId);
}
