package com.nhnacademy.springjpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cert")
public class CertificationController {
  @GetMapping("/resident/{serialNumber}")
  public String getResidentResidentRegister(@PathVariable(name="serialNumber") int serialNumber, Model model) {
    return "cert/resident_register";
  }
  @GetMapping("/family/{serialNumber}")
  public String getResidentFamilyRelation(@PathVariable(name="serialNumber") int serialNumber, Model model) {
    return "cert/family_relation";
  }
  @GetMapping("/birth/{serialNumber}")
  public String getResidentBirthReport(@PathVariable(name="serialNumber") int serialNumber, Model model) {
    return "cert/birth_report";
  }
  @GetMapping("/death/{serialNumber}")
  public String getResidentDeathReport(@PathVariable(name="serialNumber") int serialNumber, Model model) {
    return "cert/death_report";
  }

}
