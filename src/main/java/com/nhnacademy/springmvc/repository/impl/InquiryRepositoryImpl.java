package com.nhnacademy.springmvc.repository.impl;

import com.nhnacademy.springmvc.domain.Inquiry;
import com.nhnacademy.springmvc.repository.InquiryRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

@Repository
public class InquiryRepositoryImpl implements InquiryRepository {
  private final HashMap<String, List<Inquiry>> inquiryMap = new HashMap<>();

  @Override
  public boolean isExist(String id) {
    return inquiryMap.containsKey(id);
  }

  @Override
  public List<Inquiry> findById(String id) {
    List<Inquiry> inquiryList = inquiryMap.get(id);
    return Objects.nonNull(inquiryList) ? inquiryList : Collections.emptyList();
  }

  @Override
  public List<Inquiry> findNotResponseListById(String id) {
    List<Inquiry> inquiryList = inquiryMap.get(id);
    if(Objects.isNull(inquiryList))
      return Collections.emptyList();
    return inquiryList.stream().filter(inquiry -> !inquiry.isResponse()).sorted(Comparator.comparing(Inquiry::getCreated)).collect(
        Collectors.toList());
  }

  public void save(Inquiry inquiry) {
    if(!inquiryMap.containsKey(inquiry.getAuthor()))
      inquiryMap.put(inquiry.getAuthor(), new ArrayList<>());

    inquiryMap.get(inquiry.getAuthor()).add(inquiry);
  }
}
