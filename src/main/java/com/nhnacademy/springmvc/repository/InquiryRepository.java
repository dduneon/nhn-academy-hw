package com.nhnacademy.springmvc.repository;

import com.nhnacademy.springmvc.domain.Inquiry;
import java.util.TreeSet;
import org.springframework.web.multipart.MultipartFile;

public interface InquiryRepository {
  boolean isExist(String id);
  TreeSet<Inquiry> findByAuthor(String id);
   TreeSet<Inquiry> findByIdWithCategory(String id, String category);

  void save(Inquiry inquiry, MultipartFile[] files);

  TreeSet<Inquiry> getNotRespondedInquiry();

  }
