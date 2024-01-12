package com.nhnacademy.springmvc.repository;

import com.nhnacademy.springmvc.domain.Inquiry;
import java.util.List;

public interface InquiryRepository {
  boolean isExist(String id);
  List<Inquiry> findById(String id);
  List<Inquiry> findNotResponseListById(String id);
  void save(Inquiry inquiry);
}
