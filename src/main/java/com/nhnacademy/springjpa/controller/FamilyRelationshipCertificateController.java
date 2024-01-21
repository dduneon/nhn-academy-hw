package com.nhnacademy.springjpa.controller;

import com.nhnacademy.springjpa.domain.dto.familyrelationship.FamilyRelationshipCertificateDTO;
import com.nhnacademy.springjpa.service.IssueCertificateService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cert/family")
public class FamilyRelationshipCertificateController {
  private final IssueCertificateService issueCertificateService;

  public FamilyRelationshipCertificateController(IssueCertificateService issueCertificateService) {
    this.issueCertificateService = issueCertificateService;
  }

  @GetMapping("/{serialNumber}")
  public String getResidentFamilyRelation(@PathVariable(name="serialNumber") int serialNumber, Model model) {
    FamilyRelationshipCertificateDTO familyRelationshipCertificate = issueCertificateService.getFamilyRelationshipData(serialNumber);

    model.addAttribute("FAMILY_RELATIONSHIP_CERT_INFO", familyRelationshipCertificate);
    return "cert/family_relation";
  }
}
