package com.nhnacademy.springjpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cert/family")
public class FamilyRelationController {

  @GetMapping("/{serialNumber}")
  public String getResidentFamilyRelation(@PathVariable(name="serialNumber") int serialNumber, Model model) {
    return "cert/family_relation";
  }
}
