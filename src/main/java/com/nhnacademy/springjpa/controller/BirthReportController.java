package com.nhnacademy.springjpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cert/birth")
public class BirthReportController {
  @GetMapping("/{serialNumber}")
  public String getResidentBirthReport(@PathVariable(name="serialNumber") int serialNumber, Model model) {
    return "cert/birth_report";
  }
}
