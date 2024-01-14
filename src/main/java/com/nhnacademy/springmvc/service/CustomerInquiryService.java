package com.nhnacademy.springmvc.service;

import com.nhnacademy.springmvc.domain.Answer;
import com.nhnacademy.springmvc.domain.Inquiry;
import com.nhnacademy.springmvc.domain.InquiryPostRequest;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface CustomerInquiryService {
  void addUserInquiry(InquiryPostRequest inquiryPostRequest, MultipartFile[] files);
  List<Inquiry> getUserInquiriesByCategory(String userId, String category);
  Inquiry getSpecifiedInquiry(String userId, long inquiryId);
  Answer getSpecifiedAnswer(long inquiryId);
}
