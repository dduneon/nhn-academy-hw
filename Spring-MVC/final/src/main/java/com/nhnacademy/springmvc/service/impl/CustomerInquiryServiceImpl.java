package com.nhnacademy.springmvc.service.impl;

import com.nhnacademy.springmvc.domain.Answer;
import com.nhnacademy.springmvc.domain.Inquiry;
import com.nhnacademy.springmvc.domain.InquiryPostRequest;
import com.nhnacademy.springmvc.exception.FileUploadFailedException;
import com.nhnacademy.springmvc.exception.FilenameExtensionNotSupportedException;
import com.nhnacademy.springmvc.exception.InquiryNotFoundException;
import com.nhnacademy.springmvc.repository.AnswerRepository;
import com.nhnacademy.springmvc.repository.InquiryRepository;
import com.nhnacademy.springmvc.service.CustomerInquiryService;
import com.nhnacademy.springmvc.util.FileCheckUtils;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
public class CustomerInquiryServiceImpl implements CustomerInquiryService {
  private final InquiryRepository inquiryRepository;
  private final AnswerRepository answerRepository;

  @Value("${UPLOAD_DIR}")
  private String UPLOAD_DIR;

  public CustomerInquiryServiceImpl(InquiryRepository inquiryRepository,
      AnswerRepository answerRepository) {
    this.inquiryRepository = inquiryRepository;
    this.answerRepository = answerRepository;
  }

  @Override
  public void addUserInquiry(InquiryPostRequest inquiryPostRequest, MultipartFile[] files) {
    long inquiryId = inquiryRepository.save(inquiryPostRequest, files);
    String userDir = UPLOAD_DIR + inquiryPostRequest.getAuthor() + "/" + inquiryId;

    File folder = new File(userDir);
    if (!folder.exists())
      folder.mkdirs();

    if(Objects.isNull(files))
      throw new FileUploadFailedException();

    for(MultipartFile file: files) {
      log.debug("addUserInquiry(): fileName -> {}", file.getOriginalFilename());
      if(!StringUtils.hasText(file.getOriginalFilename()))
        break;
      try {
        log.debug("{}", file.getOriginalFilename());
        if(FileCheckUtils.CheckFilenameExtension(file.getOriginalFilename())) {
          file.transferTo(Paths.get(userDir + "/" + file.getOriginalFilename()));
        } else {
          throw new FilenameExtensionNotSupportedException();
        }
      } catch (IOException ioException) {
        throw new FileUploadFailedException(ioException);
      }
    }
  }

  @Override
  public List<Inquiry> getUserInquiriesByCategory(String userId, String category) {
    return inquiryRepository.findByUserId(userId).entrySet()
        .stream()
        .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
        .map(Map.Entry::getValue)
        .filter(inquiry -> category.equals("전체 보기") || inquiry.getCategory().equals(category))
        .collect(Collectors.toList());
  }

  @Override
  public Inquiry getSpecifiedInquiry(String userId, long inquiryId) {
    Inquiry inquiry = inquiryRepository.findByUserId(userId).get(inquiryId);
    if(Objects.isNull(inquiry))
      throw new InquiryNotFoundException();
    return inquiry;
  }

  @Override
  public Answer getSpecifiedAnswer(long inquiryId) {
    return answerRepository.findByInquiryId(inquiryId);
  }
}
