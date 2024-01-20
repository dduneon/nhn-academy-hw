package com.nhnacademy.springjpa.controller;

import com.nhnacademy.springjpa.domain.PagePosDTO;
import com.nhnacademy.springjpa.domain.IssuableResidentDTO;
import com.nhnacademy.springjpa.service.ResidentService;
import java.util.List;
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
    Page<IssuableResidentDTO> pageableList = residentService.getMainResidentData(pageable);

    // log start
    List<IssuableResidentDTO> list = pageableList.getContent();
    for(IssuableResidentDTO r: list) {
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

    model.addAttribute("PAGE_INFO", new PagePosDTO(navigationStartPos, navigationEndPos));
    model.addAttribute("CURRENT_PAGE_LIST", pageableList);
    return "main";
  }
}
