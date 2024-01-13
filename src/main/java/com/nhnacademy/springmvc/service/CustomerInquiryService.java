package com.nhnacademy.springmvc.service;

import com.nhnacademy.springmvc.domain.InquiryPostRequest;
import org.springframework.web.multipart.MultipartFile;

public interface CustomerInquiryService {
  void addUserInquiry(InquiryPostRequest inquiryPostRequest, MultipartFile[] files);
}
