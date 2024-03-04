package com.nhnacademy.springjpa.controller;

import com.nhnacademy.springjpa.domain.dto.birth.BirthReportDTO;
import com.nhnacademy.springjpa.service.BirthDeathReportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cert/birth")
public class BirthReportController {
  private final BirthDeathReportService birthDeathReportService;

  public BirthReportController(BirthDeathReportService birthDeathReportService) {
    this.birthDeathReportService = birthDeathReportService;
  }

  @GetMapping("/{serialNumber}")
  public String getResidentBirthReport(@PathVariable(name="serialNumber") int serialNumber, Model model) {
    BirthReportDTO birthReport = birthDeathReportService.getBirthReportData(serialNumber);
    model.addAttribute("BIRTH_REPORT_DATA", birthReport);
    return "cert/birth_report";
  }
}
