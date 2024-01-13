package com.nhnacademy.springmvc.repository.impl;

import com.nhnacademy.springmvc.domain.Inquiry;
import com.nhnacademy.springmvc.domain.InquiryPostRequest;
import com.nhnacademy.springmvc.repository.InquiryRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
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
  private final Map<String, Map<Long, Inquiry>> inquiryMap = new HashMap<>();
  private final Comparator<Inquiry> sortedByCreated = ((inquiry1, inquiry2) -> inquiry2.getCreated().compareTo(inquiry1.getCreated()));

  @Override
  public boolean isExist(String author) {
    return inquiryMap.containsKey(author);
  }

  @Override
  public TreeSet<Inquiry> findByAuthor(String id) {
    Map<Long, Inquiry> userInquiryMap = inquiryMap.get(id);
    List<Inquiry> userInquiryList = new ArrayList<>();
    for(Long : userInquiryMap.keySet().stream().sorted(Comparator.naturalOrder()).collect(
        Collectors.toList()))
    userInquiryMap.
    return Objects.nonNull(userInquirySet) ? userInquirySet : new TreeSet<>(Collections.emptySet());
  }

  @Override
  public TreeSet<Inquiry> findByIdWithCategory(String id, String category) {
    TreeSet<Inquiry> userInquirySet = findByAuthor(id);
    //todo 분리 or 그대로?
    if(category.equals("전체 보기"))
      return userInquirySet;
    return userInquirySet.stream().filter(inquiry -> inquiry.getCategory().equals(category))
        .collect(Collectors.toCollection(() -> new TreeSet<>(sortedByCreated)));
  }

  public void save(InquiryPostRequest inquiryPostRequest, MultipartFile[] files) {
    if(!inquiryMap.containsKey(inquiryPostRequest.getAuthor()))
      inquiryMap.put(inquiryPostRequest.getAuthor(), new TreeSet<>(sortedByCreated));

    long id =
    inquiryMap.get(inquiryPostRequest.getAuthor()).add(inquiryPostRequest);
  }

  public TreeSet<Inquiry> getNotRespondedInquiry() {
    TreeSet<Inquiry> notRespondedInquirySet = new TreeSet<>(sortedByCreated);
    Collection<TreeSet<Inquiry>> inquiries = inquiryMap.values();
    for(TreeSet<Inquiry> inquirySet: inquiries) {
      for(Inquiry inquiry: inquirySet) {
        if(!inquiry.isResponded())
          notRespondedInquirySet.add(inquiry);
      }
    }
    return notRespondedInquirySet;
  }
}
