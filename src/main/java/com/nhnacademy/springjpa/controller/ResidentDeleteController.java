package com.nhnacademy.springjpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/delete")
public class ResidentDeleteController {
  @GetMapping("/{serialNumber}")
  public String deleteResident() {
    return "main";
  }
}
