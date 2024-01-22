package com.nhnacademy.springjpa.controller.rest;

import com.nhnacademy.springjpa.domain.request.HouseholdMovementPostRequest;
import com.nhnacademy.springjpa.domain.request.HouseholdMovementPutRequest;
import com.nhnacademy.springjpa.service.HouseholdMovementService;
import java.time.LocalDate;
import javax.validation.Valid;
import javax.validation.ValidationException;
import org.springframework.format.annotation.DateTimeFormat;
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
@RequestMapping("/household")
public class RestHouseholdMovementController {
  private final HouseholdMovementService householdMovementService;

  public RestHouseholdMovementController(HouseholdMovementService householdMovementService) {
    this.householdMovementService = householdMovementService;
  }

  @PostMapping("/{householdSerialNumber}/movement")
  @ResponseStatus(HttpStatus.CREATED)
  public void createHouseholdMovement(@PathVariable(name = "householdSerialNumber") int householdSerialNumber,
      @Valid @RequestBody HouseholdMovementPostRequest request, BindingResult bindingResult) {
    if(bindingResult.hasErrors())
      throw new ValidationException("유효성 검증이 실패하였습니다");

    householdMovementService.createHouseholdMovement(householdSerialNumber, request);
  }

  @PutMapping("/{householdSerialNumber}/movement/{reportDate}")
  @ResponseStatus(HttpStatus.OK)
  public void updateHouseholdMovement(@PathVariable(name="householdSerialNumber") int householdSerialNumber, @PathVariable(name="reportDate") @DateTimeFormat(pattern = "yyyyMMdd")
      LocalDate reportDate, @Valid @RequestBody HouseholdMovementPutRequest request, BindingResult bindingResult) {
    if(bindingResult.hasErrors())
      throw new ValidationException("유효성 검증이 실패하였습니다");

    householdMovementService.updateHouseholdMovement(householdSerialNumber, reportDate, request);
  }

  @DeleteMapping("/{householdSerialNumber}/movement/{reportDate}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteHouseholdMovement(@PathVariable(name="householdSerialNumber") int householdSerialNumber, @PathVariable(name="reportDate") @DateTimeFormat(pattern = "yyyyMMdd")
  LocalDate reportDate ) {
    householdMovementService.deleteHouseholdMovement(householdSerialNumber, reportDate);
  }

}
