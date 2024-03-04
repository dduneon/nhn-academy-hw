package com.nhnacademy.springjpa.controller.rest;

import com.nhnacademy.springjpa.domain.request.ResidentPostRequest;
import com.nhnacademy.springjpa.domain.request.ResidentPutRequest;
import com.nhnacademy.springjpa.service.ResidentManageService;
import javax.validation.Valid;
import javax.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/residents")
public class RestResidentController {
  private final ResidentManageService residentManageService;

  public RestResidentController(ResidentManageService residentManageService) {
    this.residentManageService = residentManageService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void createResident(@Valid @RequestBody ResidentPostRequest request, BindingResult bindingResult) {
    if(bindingResult.hasErrors())
      throw new ValidationException("유효성 검증이 실패하였습니다");
    residentManageService.saveResident(request);
  }

  @PutMapping("/{serialNumber}")
  @ResponseStatus(HttpStatus.OK)
  public void updateResident(@PathVariable(name = "serialNumber") int serialNumber, @Valid @RequestBody ResidentPutRequest request, BindingResult bindingResult){
    if(bindingResult.hasErrors())
      throw new ValidationException("유효성 검증이 실패하였습니다");

    residentManageService.updateResident(serialNumber, request);
  }
}
