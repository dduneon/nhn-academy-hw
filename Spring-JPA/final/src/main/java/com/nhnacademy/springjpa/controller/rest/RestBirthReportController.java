package com.nhnacademy.springjpa.controller.rest;

import com.nhnacademy.springjpa.domain.request.BirthReportPostRequest;
import com.nhnacademy.springjpa.domain.request.BirthReportPutRequest;
import com.nhnacademy.springjpa.service.BirthDeathReportService;
import javax.validation.Valid;
import javax.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/residents")
public class RestBirthReportController {
  private final BirthDeathReportService birthDeathReportService;

  public RestBirthReportController(BirthDeathReportService birthDeathReportService) {
    this.birthDeathReportService = birthDeathReportService;
  }

  @PostMapping("/{serialNumber}/birth")
  @ResponseStatus(HttpStatus.CREATED)
  public void createBirthReport(@PathVariable(name="serialNumber") int serialNumber, @Valid @RequestBody
      BirthReportPostRequest request, BindingResult bindingResult) {
    if(bindingResult.hasErrors())
      throw new ValidationException("유효성 검증이 실패하였습니다");

    birthDeathReportService.createBirthReport(serialNumber, request);
  }

  @PutMapping("/{serialNumber}/birth/{targetSerialNumber}")
  @ResponseStatus(HttpStatus.OK)
  public void updateBirthReport(@PathVariable(name="serialNumber") int serialNumber, @PathVariable(name="targetSerialNumber") int targetSerialNumber,
      @Valid @RequestBody BirthReportPutRequest request, BindingResult bindingResult) {
    if(bindingResult.hasErrors())
      throw new ValidationException("유효성 검증이 실패하였습니다");

    birthDeathReportService.updateBirthReport(serialNumber, targetSerialNumber, request);
  }

  @DeleteMapping("/{serialNumber}/birth/{targetSerialNumber}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteBirthReport(@PathVariable(name="serialNumber") int serialNumber, @PathVariable(name="targetSerialNumber") int targetSerialNumber) {
    birthDeathReportService.deleteBirthReport(serialNumber, targetSerialNumber);
  }
}
