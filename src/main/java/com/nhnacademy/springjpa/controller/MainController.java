package com.nhnacademy.springjpa.controller;

import com.nhnacademy.springjpa.domain.BirthDeathReportResidentDTO;
import com.nhnacademy.springjpa.domain.CertificateIssueDTO;
import com.nhnacademy.springjpa.domain.ResidentDTO;
import com.nhnacademy.springjpa.service.ResidentService;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/")
public class MainController {
  private final ResidentService residentService;

  public MainController(ResidentService residentService) {
    this.residentService = residentService;
  }

  @GetMapping
  public String getResidentList(@PageableDefault(page = 0, size = 5) Pageable pageable, Model model) {
    Page<ResidentDTO> pageableList = residentService.getMainResidentData(pageable);
    List<ResidentDTO> list = pageableList.getContent();

    // log start
    for(ResidentDTO r: list) {
      log.debug("getResidentList(): {}, {}", r.getName(), r.getResidentSerialNumber());
      log.debug("birthDeathReportResident -> {}", r.getBirthDeathReportResident().toString());
      log.debug("certificateIssue -> {}", r.getCertificateIssue().toString());
    }
    // log end
    int residentListCount = residentService.getResidentCount();
    int navigationStartPos = 0;
    int navigationEndPos = residentListCount / pageable.getPageSize()
        - (residentListCount % pageable.getPageSize() == 0 ? 1 : 0);
    navigationEndPos = Math.max(navigationEndPos, 0);

    model.addAttribute("CURRENT_PAGE_LIST", pageableList);
    model.addAttribute("CURRENT_PAGE_POS", pageable.getPageNumber());
    model.addAttribute("NAVIGATION_START_POS", navigationStartPos);
    model.addAttribute("NAVIGATION_END_POS", navigationEndPos);
    return "main";
  }
}
