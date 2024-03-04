package com.nhnacademy.springjpa.controller;

import com.nhnacademy.springjpa.domain.dto.death.DeathReportDTO;
import com.nhnacademy.springjpa.service.BirthDeathReportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cert/death")
public class DeathReportController {
  private final BirthDeathReportService birthDeathReportService;

  public DeathReportController(BirthDeathReportService birthDeathReportService) {
    this.birthDeathReportService = birthDeathReportService;
  }
  @GetMapping("/{serialNumber}")
  public String getResidentDeathReport(@PathVariable(name="serialNumber") int serialNumber, Model model) {
    DeathReportDTO deathReport = birthDeathReportService.getDeathReportData(serialNumber);
    model.addAttribute("DEATH_REPORT_DATA", deathReport);
    return "cert/death_report";
  }
}
