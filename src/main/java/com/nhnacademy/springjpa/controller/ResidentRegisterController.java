package com.nhnacademy.springjpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cert/register")
public class ResidentRegisterController {
  @GetMapping("/{serialNumber}")
  public String getResidentResidentRegister(@PathVariable(name="serialNumber") int serialNumber, Model model) {
    return "cert/resident_register";
  }
}
