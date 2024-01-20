package com.nhnacademy.springjpa.controller;

import com.nhnacademy.springjpa.domain.dto.residentregister.ResidentRegisterDTO;
import com.nhnacademy.springjpa.service.IssueCertificateService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cert/resident")
public class ResidentRegisterController {
  private final IssueCertificateService issueCertificateService;

  public ResidentRegisterController(IssueCertificateService issueCertificateService) {
    this.issueCertificateService = issueCertificateService;
  }

  @GetMapping("/{serialNumber}")
  public String getResidentResidentRegister(@PathVariable(name="serialNumber") int serialNumber, Model model) {
    ResidentRegisterDTO residentRegister = issueCertificateService.getResidentRegisterData(serialNumber);

    model.addAttribute("RESIDENT_REGISTER_INFO", residentRegister);
    return "cert/resident_register";
  }
}
