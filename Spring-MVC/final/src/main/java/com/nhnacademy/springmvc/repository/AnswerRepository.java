package com.nhnacademy.springmvc.repository;

import com.nhnacademy.springmvc.domain.Answer;

public interface AnswerRepository {
  boolean save(Long inquiryId, Answer answer);

  Answer findByInquiryId(Long inquiryId);
}
