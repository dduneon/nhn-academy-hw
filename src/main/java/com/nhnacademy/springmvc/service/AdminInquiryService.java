package com.nhnacademy.springmvc.service;

import com.nhnacademy.springmvc.domain.Inquiry;
import java.util.List;

public interface AdminInquiryService {
  List<Inquiry> getNotRespondInquiries();
}
