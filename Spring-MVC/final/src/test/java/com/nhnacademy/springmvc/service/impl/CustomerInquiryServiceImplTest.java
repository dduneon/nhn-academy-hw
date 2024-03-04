package com.nhnacademy.springmvc.service.impl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nhnacademy.springmvc.domain.Answer;
import com.nhnacademy.springmvc.domain.Inquiry;
import com.nhnacademy.springmvc.domain.InquiryPostRequest;
import com.nhnacademy.springmvc.exception.FileUploadFailedException;
import com.nhnacademy.springmvc.exception.FilenameExtensionNotSupportedException;
import com.nhnacademy.springmvc.exception.InquiryNotFoundException;
import com.nhnacademy.springmvc.repository.AnswerRepository;
import com.nhnacademy.springmvc.repository.InquiryRepository;
import com.nhnacademy.springmvc.service.CustomerInquiryService;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

class CustomerInquiryServiceImplTest {
  private InquiryRepository inquiryRepository;
  private AnswerRepository answerRepository;
  private CustomerInquiryService customerInquiryService;

  @BeforeEach
  public void setUp() {
    inquiryRepository = mock(InquiryRepository.class);
    answerRepository = mock(AnswerRepository.class);
    customerInquiryService = new CustomerInquiryServiceImpl(inquiryRepository, answerRepository);
  }

  private static InquiryPostRequest inquiryPostRequest = new InquiryPostRequest("author", "title", "category", "content");
  private static MultipartFile[] filesTestCases = {
      new MockMultipartFile("supportedFile", "file1.jpg", "text/plain", "Test File Content".getBytes()),
      new MockMultipartFile("notSupportedFile", "file2.txt", "text/plain", "Test File Content".getBytes()),
      new MockMultipartFile("emptyNameFile", "", "text/plain", "Test File Content".getBytes()),
  };

  @Test
  void addUserInquirySuccessTest() {
    when(inquiryRepository.save(any(InquiryPostRequest.class), any(MultipartFile[].class))).thenReturn(1L);
    assertDoesNotThrow(()->customerInquiryService.addUserInquiry(inquiryPostRequest, new MultipartFile[]{filesTestCases[0]}));
  }

  @Test
  void addUserInquiryNotSupportedTest() {
    when(inquiryRepository.save(any(InquiryPostRequest.class), any(MultipartFile[].class))).thenReturn(1L);
    assertThrows(FilenameExtensionNotSupportedException.class, ()->customerInquiryService.addUserInquiry(inquiryPostRequest, new MultipartFile[]{filesTestCases[1]}));
  }

  @Test
  void addUserInquiryUploadFailedTest() {
    when(inquiryRepository.save(any(InquiryPostRequest.class), any(MultipartFile[].class))).thenReturn(1L);
    assertThrows(FileUploadFailedException.class, ()->customerInquiryService.addUserInquiry(inquiryPostRequest, null));
  }

  @Test
  void addUserInquiryEmptyNameTest() {
    when(inquiryRepository.save(any(InquiryPostRequest.class), any(MultipartFile[].class))).thenReturn(1L);
    assertDoesNotThrow(() -> customerInquiryService.addUserInquiry(inquiryPostRequest, new MultipartFile[]{filesTestCases[2]}));
  }

  static List<Inquiry> allInquiryTestCases = List.of(
      new Inquiry(1L, "author", "title", "category1", "content", null, null, false),
      new Inquiry(2L, "author", "title", "category2", "content", null, null, false),
      new Inquiry(3L, "author", "title", "category3", "content", null, null, false),
      new Inquiry(4L, "author", "title", "category4", "content", null, null, false)
  );
  @Test
  void getUserInquiriesByCategory() {
    when(inquiryRepository.findByUserId(anyString())).thenReturn(
        Map.of(
            1L, allInquiryTestCases.get(0),
            2L, allInquiryTestCases.get(1),
            3L, allInquiryTestCases.get(2),
            4L, allInquiryTestCases.get(3)
        )
    );

    List<Inquiry> inquiryExpectedCategory1 = customerInquiryService.getUserInquiriesByCategory("userId", "category1");
    for(Inquiry inquiry: inquiryExpectedCategory1)
      assertEquals("category1", inquiry.getCategory());

    List<Inquiry> inquiryExpectedAllCategory = customerInquiryService.getUserInquiriesByCategory("userId", "전체 보기");
    assertEquals(allInquiryTestCases.size(), inquiryExpectedAllCategory.size());
  }

  @Test
  void getSpecifiedInquiry() {
    when(inquiryRepository.findByUserId(anyString())).thenReturn(
        Map.of(
            1L, allInquiryTestCases.get(0),
            2L, allInquiryTestCases.get(1),
            3L, allInquiryTestCases.get(2),
            4L, allInquiryTestCases.get(3)
        )
    );

    Inquiry inquiry = customerInquiryService.getSpecifiedInquiry("userId", 1L);
    assertEquals(allInquiryTestCases.get(0), inquiry);

    assertThrows(InquiryNotFoundException.class, () -> customerInquiryService.getSpecifiedInquiry("userId", 5L));
  }

  @Test
  void getSpecifiedAnswer() {
    Answer answer = new Answer(1L, "adminId", "content");
    when(answerRepository.findByInquiryId(1L)).thenReturn(answer);
    Answer actual = customerInquiryService.getSpecifiedAnswer(1L);

    assertEquals(answer, actual);
    verify(answerRepository).findByInquiryId(1L);
  }
}