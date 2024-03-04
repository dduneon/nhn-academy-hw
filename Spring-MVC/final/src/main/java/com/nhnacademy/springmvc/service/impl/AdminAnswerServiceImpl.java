package com.nhnacademy.springmvc.service.impl;

import com.nhnacademy.springmvc.domain.Answer;
import com.nhnacademy.springmvc.exception.AddAnswerFailedException;
import com.nhnacademy.springmvc.repository.AnswerRepository;
import com.nhnacademy.springmvc.service.AdminAnswerService;
import org.springframework.stereotype.Service;

@Service
public class AdminAnswerServiceImpl implements AdminAnswerService {
  private final AnswerRepository answerRepository;

  public AdminAnswerServiceImpl(AnswerRepository answerRepository) {
    this.answerRepository = answerRepository;
  }

  public void addInquiryAnswer(long inquiryId, Answer answer) {
    if(!answerRepository.save(inquiryId, answer))
      throw new AddAnswerFailedException();
  }
}
