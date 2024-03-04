package com.nhnacademy.springmvc.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.nhnacademy.springmvc.domain.Answer;
import com.nhnacademy.springmvc.domain.Inquiry;
import com.nhnacademy.springmvc.exception.AddAnswerFailedException;
import com.nhnacademy.springmvc.exception.NotModifiedAnswerStatusException;
import com.nhnacademy.springmvc.repository.InquiryRepository;
import com.nhnacademy.springmvc.service.AdminInquiryService;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class AdminInquiryServiceImplTest {
  private AdminInquiryService adminInquiryService;
  private InquiryRepository inquiryRepository;

  @BeforeEach
  public void setUp() {
    inquiryRepository = mock(InquiryRepository.class);
    adminInquiryService = new AdminInquiryServiceImpl(inquiryRepository);
  }

  static List<Inquiry> allInquiryTestCases = List.of(
      new Inquiry(1L, "author", "title", "category", "content", null, null, false),
      new Inquiry(2L, "author", "title", "category", "content", null, null, true),
      new Inquiry(3L, "author", "title", "category", "content", null, null, false),
      new Inquiry(4L, "author", "title", "category", "content", null, null, true)
  );

  @Test
  void getNotRespondInquiries() {
    when(inquiryRepository.findAll()).thenReturn(
        Map.of(
            1L, allInquiryTestCases.get(0),
            2L, allInquiryTestCases.get(1),
            3L, allInquiryTestCases.get(2),
            4L, allInquiryTestCases.get(3)
        )
    );
    List<Inquiry> expectedList = List.of(
        allInquiryTestCases.get(2), allInquiryTestCases.get(0)
    );
    List<Inquiry> actualList = adminInquiryService.getNotRespondInquiries();
    assertEquals(expectedList.size(), actualList.size());
    assertEquals(expectedList.get(0).getId(), actualList.get(0).getId());
    assertEquals(expectedList.get(1).getId(), actualList.get(1).getId());
  }

  @Test
  void getInquiryById() {
    when(inquiryRepository.findAll()).thenReturn(
        Map.of(
            1L, allInquiryTestCases.get(0),
            2L, allInquiryTestCases.get(1),
            3L, allInquiryTestCases.get(2),
            4L, allInquiryTestCases.get(3)
        )
    );
    Inquiry actualInquiry = adminInquiryService.getInquiryById(1);
    assertEquals(allInquiryTestCases.get(0).getId(), actualInquiry.getId());
  }

  @Test
  void modifyAnsweredStatusUpdateFailedTest() {
    when(inquiryRepository.updateAnsweredStatus(anyLong(), anyString())).thenReturn(false);

    assertThrows(NotModifiedAnswerStatusException.class, () -> adminInquiryService.modifyAnsweredStatus(1, "test"));
  }
}