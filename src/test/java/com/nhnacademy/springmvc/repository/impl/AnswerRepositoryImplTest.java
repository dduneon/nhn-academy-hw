package com.nhnacademy.springmvc.repository.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.nhnacademy.springmvc.domain.Answer;
import com.nhnacademy.springmvc.repository.AnswerRepository;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class AnswerRepositoryImplTest {
  private AnswerRepository answerRepository;

  @BeforeEach
  void setUp() {
    answerRepository = new AnswerRepositoryImpl();
  }

  @Test
  void saveTest() {
    assertTrue(answerRepository.save(1L, new Answer(1L, "tes", "test")));
    assertFalse(answerRepository.save(1L, null));
  }

  @Test
  void findByInquiryIdTest() {
    Answer answer = mock(Answer.class);
    answerRepository.save(1L, answer);

    Answer actual = answerRepository.findByInquiryId(1L);
    assertEquals(answer, actual);
  }
}