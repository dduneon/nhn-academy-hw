package com.nhnacademy.springjpa.controller.rest;

import com.nhnacademy.springjpa.domain.request.HouseholdPostRequest;
import com.nhnacademy.springjpa.service.HouseholdManageService;
import javax.validation.Valid;
import javax.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/household")
public class RestHouseholdController {
  private final HouseholdManageService householdManageService;

  public RestHouseholdController(HouseholdManageService householdManageService) {
    this.householdManageService = householdManageService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void createHousehold(@Valid @RequestBody HouseholdPostRequest request, BindingResult bindingResult) {
    if(bindingResult.hasErrors())
      throw new ValidationException("유효성 검증이 실패하였습니다");

    householdManageService.createHousehold(request);
  }

  @DeleteMapping("/{householdSerialNumber}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteHousehold(@PathVariable int householdSerialNumber) {
    householdManageService.deleteHousehold(householdSerialNumber);
  }

}
