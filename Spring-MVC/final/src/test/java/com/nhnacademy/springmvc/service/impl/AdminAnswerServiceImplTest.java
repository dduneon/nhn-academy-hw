package com.nhnacademy.springmvc.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.nhnacademy.springmvc.domain.Answer;
import com.nhnacademy.springmvc.exception.AddAnswerFailedException;
import com.nhnacademy.springmvc.repository.AnswerRepository;
import com.nhnacademy.springmvc.service.AdminAnswerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class AdminAnswerServiceImplTest {
  private AdminAnswerService adminAnswerService;
  private AnswerRepository answerRepository;

  @BeforeEach
  public void setUp() {
    answerRepository = mock(AnswerRepository.class);
    adminAnswerService = new AdminAnswerServiceImpl(answerRepository);
  }

  @Test
  void addInquiryAnswerFailTest() {
    Answer answer = mock(Answer.class);
    when(answerRepository.save(anyLong(), any())).thenReturn(false);

    assertThrows(AddAnswerFailedException.class, () -> adminAnswerService.addInquiryAnswer(1, answer));
  }

  @Test
  void addInquiryAnswerSuccessTest() {
    Answer answer = mock(Answer.class);
    when(answerRepository.save(1L, answer)).thenReturn(true);

    assertDoesNotThrow(() -> adminAnswerService.addInquiryAnswer(1, answer));
  }
}