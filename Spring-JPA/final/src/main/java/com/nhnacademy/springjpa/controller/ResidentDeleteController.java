package com.nhnacademy.springjpa.controller;

import com.nhnacademy.springjpa.exception.ResidentDeleteFailedException;
import com.nhnacademy.springjpa.service.ResidentManageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/delete")
public class ResidentDeleteController {
  private final ResidentManageService residentManageService;

  public ResidentDeleteController(ResidentManageService residentManageService) {
    this.residentManageService = residentManageService;
  }

  @GetMapping("/{serialNumber}")
  public String deleteResident(@PathVariable(name="serialNumber") int serialNumber) {
    // 1. 세대주인지 확인
    // 2. 세대구성원 있는지 확인
    residentManageService.deleteResident(serialNumber);
    return "redirect:/";
  }
}
