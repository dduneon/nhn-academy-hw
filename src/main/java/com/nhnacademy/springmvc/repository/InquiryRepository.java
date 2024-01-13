package com.nhnacademy.springmvc.repository;

import com.nhnacademy.springmvc.domain.Inquiry;
import com.nhnacademy.springmvc.domain.InquiryPostRequest;
import java.util.List;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

public interface InquiryRepository {
  Map<Long, Inquiry> findByUserId(String userId);
  void save(InquiryPostRequest inquiryPostRequest, MultipartFile[] files);
  Map<Long, Inquiry> findAll();
  }
