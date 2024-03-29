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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Repository
public class InquiryRepositoryImpl implements InquiryRepository {
  private final Map<String, Map<Long, Inquiry>> inquiryDataBase = new HashMap<>();

  @Override
  public void initUser(String userId) {
    if(!inquiryDataBase.containsKey(userId))
      inquiryDataBase.put(userId, new HashMap<>());
  }

  @Override
  public Map<Long, Inquiry> findByUserId(String userId) {
    initUser(userId);
    Map<Long, Inquiry> userInquiryMap = inquiryDataBase.get(userId);
    log.debug("findByUserId(): map size -> {}", userInquiryMap.size());
    return Objects.nonNull(userInquiryMap) ? userInquiryMap : Collections.emptyMap();
  }

  @Override
  public long save(InquiryPostRequest inquiryPostRequest, MultipartFile[] files) {
    String userId = inquiryPostRequest.getAuthor();
    log.debug("save(): userId -> {}", userId);

    initUser(userId);

    long newInquiryId = inquiryDataBase.values()
        .stream()
        .flatMap(innerMap -> innerMap.keySet().stream())
        .max(Long::compare)
        .map(id -> id+1)
        .orElse(1L);

    log.debug("save(): newInquiryId -> {}", newInquiryId);

    Inquiry inquiry = new Inquiry(newInquiryId, inquiryPostRequest, files);
    inquiryDataBase.get(userId).put(newInquiryId, inquiry);
    log.debug("save(): {}", inquiryDataBase.get(userId).get(newInquiryId).getTitle());
    return newInquiryId;
  }

  @Override
  public Map<Long, Inquiry> findAll() {
    return inquiryDataBase.values().stream()
        .flatMap(innerMap -> innerMap.entrySet().stream())
        .collect(Collectors.toMap(
            Map.Entry::getKey,
            Map.Entry::getValue,
            (existing, replacement) -> existing
        ));
  }

  @Override
  public boolean updateAnsweredStatus(Long inquiryId, String author) {
    Inquiry inquiry = findByUserId(author).get(inquiryId);
    if(Objects.nonNull(inquiry)) {
      inquiry.setAnswered(true);
      return true;
    }
    return false;
  }
}
