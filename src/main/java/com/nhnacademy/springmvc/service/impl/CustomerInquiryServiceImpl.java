package com.nhnacademy.springmvc.service.impl;

import com.nhnacademy.springmvc.domain.Inquiry;
import com.nhnacademy.springmvc.domain.InquiryPostRequest;
import com.nhnacademy.springmvc.exception.FileUploadFailedException;
import com.nhnacademy.springmvc.exception.FilenameExtensionNotSupportedException;
import com.nhnacademy.springmvc.repository.InquiryRepository;
import com.nhnacademy.springmvc.service.CustomerInquiryService;
import com.nhnacademy.springmvc.util.FileCheckUtils;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
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

  @Value("${UPLOAD_DIR}")
  private String UPLOAD_DIR;

  public CustomerInquiryServiceImpl(InquiryRepository inquiryRepository) {
    this.inquiryRepository = inquiryRepository;
  }

  @Override
  public void addUserInquiry(InquiryPostRequest inquiryPostRequest, MultipartFile[] files) {
    inquiryRepository.save(inquiryPostRequest, files);
    log.debug("addUserInquiry(): number of upload file -> {}", files.length);
    for(MultipartFile file: files) {
      log.debug("addUserInquiry(): fileName -> {}", file.getOriginalFilename());
      if(!StringUtils.hasText(file.getOriginalFilename()))
        break;
      try {
        log.debug("{}", file.getOriginalFilename());
        if(FileCheckUtils.CheckFilenameExtension(file.getOriginalFilename())) {
          file.transferTo(Paths.get(UPLOAD_DIR + file.getOriginalFilename()));
        } else {
          throw new FilenameExtensionNotSupportedException();
        }
      } catch (IOException ioException) {
        throw new FileUploadFailedException();
      }
    }
  }

  @Override
  public List<Inquiry> getUserInquiriesByCategory(String userId, String category) {
    return inquiryRepository.findByUserId(userId).entrySet()
        .stream()
        .sorted(Map.Entry.comparingByKey())
        .map(Map.Entry::getValue)
        .filter(inquiry -> inquiry.getCategory().equals(category))
        .collect(Collectors.toList());
  }
}
