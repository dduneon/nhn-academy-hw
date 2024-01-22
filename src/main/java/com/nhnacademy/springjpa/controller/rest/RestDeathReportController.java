package com.nhnacademy.springjpa.controller.rest;

import com.nhnacademy.springjpa.domain.request.BirthReportPostRequest;
import com.nhnacademy.springjpa.domain.request.BirthReportPutRequest;
import com.nhnacademy.springjpa.domain.request.DeathReportPostRequest;
import com.nhnacademy.springjpa.domain.request.DeathReportPutRequest;
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
public class RestDeathReportController {
  private final BirthDeathReportService birthDeathReportService;

  public RestDeathReportController(BirthDeathReportService birthDeathReportService) {
    this.birthDeathReportService = birthDeathReportService;
  }

  @PostMapping("/{serialNumber}/death")
  @ResponseStatus(HttpStatus.CREATED)
  public void createDeathReport(@PathVariable(name="serialNumber") int serialNumber, @Valid @RequestBody
  DeathReportPostRequest request, BindingResult bindingResult) {
    if(bindingResult.hasErrors())
      throw new ValidationException("유효성 검증이 실패하였습니다");

    birthDeathReportService.createDeathReport(serialNumber, request);
  }

  @PutMapping("/{serialNumber}/death/{targetSerialNumber}")
  @ResponseStatus(HttpStatus.OK)
  public void updateDeathReport(@PathVariable(name="serialNumber") int serialNumber, @PathVariable(name="targetSerialNumber") int targetSerialNumber,
      @Valid @RequestBody DeathReportPutRequest request, BindingResult bindingResult) {
    if(bindingResult.hasErrors())
      throw new ValidationException("유효성 검증이 실패하였습니다");

    birthDeathReportService.updateDeathReport(serialNumber, targetSerialNumber, request);
  }

  @DeleteMapping("/{serialNumber}/death/{targetSerialNumber}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteDeathReport(@PathVariable(name="serialNumber") int serialNumber, @PathVariable(name="targetSerialNumber") int targetSerialNumber) {
    birthDeathReportService.deleteDeathReport(serialNumber, targetSerialNumber);
  }
}
