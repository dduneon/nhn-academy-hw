package com.nhnacademy.springmvc.repository;

import com.nhnacademy.springmvc.domain.Answer;
import com.nhnacademy.springmvc.domain.Inquiry;
import com.nhnacademy.springmvc.domain.InquiryPostRequest;
import java.util.List;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

public interface InquiryRepository {
  void initUser(String userId);
  Map<Long, Inquiry> findByUserId(String userId);
  long save(InquiryPostRequest inquiryPostRequest, MultipartFile[] files);
  Map<Long, Inquiry> findAll();
  boolean updateAnsweredStatus(Long inquiryId, String author);
  }
