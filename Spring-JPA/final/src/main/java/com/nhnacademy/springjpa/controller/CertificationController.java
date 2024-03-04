package com.nhnacademy.springjpa.controller;

import com.nhnacademy.springjpa.domain.dto.certlist.CertificationIssueDTO;
import com.nhnacademy.springjpa.domain.dto.page.PagePosDTO;
import com.nhnacademy.springjpa.service.IssueCertificateService;
import com.oracle.wls.shaded.org.apache.xpath.operations.Mod;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.matcher.StringMatcher.Mode;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/cert")
public class CertificationController {
  private final IssueCertificateService issueCertificateService;

  public CertificationController(IssueCertificateService issueCertificateService) {
    this.issueCertificateService = issueCertificateService;
  }

  @GetMapping("/list/{serialNumber}")
  public String getCertificationList(@PathVariable(name = "serialNumber") int serialNumber, Model model,@PageableDefault(page = 0, size = 5) Pageable pageable) {
    // CERT_INFO
    CertificationIssueDTO certificationIssue = issueCertificateService.getCertificationList(serialNumber, pageable);

    int certListCount = issueCertificateService.getCertificationListCount(serialNumber);
    log.debug("getCertificationList(): certListCount -> {}", certListCount);
    int navigationStartPos = 0;
    int navigationEndPos = certListCount / pageable.getPageSize()
        - (certListCount % pageable.getPageSize() == 0 ? 1 : 0);
    navigationEndPos = Math.max(navigationEndPos, 0);

    model.addAttribute("PAGE_INFO", new PagePosDTO(navigationStartPos, navigationEndPos));
    model.addAttribute("CERT_INFO", certificationIssue);
    return "cert/list";
  }
}