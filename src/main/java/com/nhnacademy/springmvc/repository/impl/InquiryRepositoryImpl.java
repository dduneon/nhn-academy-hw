package com.nhnacademy.springmvc.repository.impl;

import com.nhnacademy.springmvc.domain.Inquiry;
import com.nhnacademy.springmvc.domain.InquiryPostRequest;
import com.nhnacademy.springmvc.repository.InquiryRepository;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
public class InquiryRepositoryImpl implements InquiryRepository {
  private final Map<String, Map<Long, Inquiry>> inquiryDataBase = new HashMap<>();

  @Override
  public Map<Long, Inquiry> findByUserId(String userId) {
    Map<Long, Inquiry> userInquiryMap = inquiryDataBase.get(userId);
    return Objects.nonNull(userInquiryMap) ? userInquiryMap : Collections.emptyMap();
  }

  @Override
  public void save(InquiryPostRequest inquiryPostRequest, MultipartFile[] files) {
    String userId = inquiryPostRequest.getAuthor();

    if(!inquiryDataBase.containsKey(userId))
      inquiryDataBase.put(userId, new HashMap<>());

    long id = inquiryDataBase.values()
        .stream()
        .flatMap(innerMap -> innerMap.keySet().stream())
        .max(Long::compare)
        .orElse(1L);

    Inquiry inquiry = new Inquiry(id, inquiryPostRequest, files);
    inquiryDataBase.get(userId).put(id, inquiry);
  }

  @Override
  public List<Inquiry> findAll() {
    return inquiryDataBase.values()
        .stream()
        .flatMap(innerMap -> innerMap.values().stream())
        .collect(Collectors.toList());
  }
}
