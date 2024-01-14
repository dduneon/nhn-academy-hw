package com.nhnacademy.springmvc.repository.impl;

import com.nhnacademy.springmvc.domain.Answer;
import com.nhnacademy.springmvc.repository.AnswerRepository;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class AnswerRepositoryImpl implements AnswerRepository {
  private final Map<Long, Answer> answerDataBase = new HashMap<>();

  @Override
  public void save(Long inquiryId, Answer answer) {
    answerDataBase.put(inquiryId, answer);
  }

  @Override
  public Answer findByInquiryId(Long inquiryId) {
    return answerDataBase.get(inquiryId);
  }

}
