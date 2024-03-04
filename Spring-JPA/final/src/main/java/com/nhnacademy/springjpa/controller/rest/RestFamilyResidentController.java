package com.nhnacademy.springjpa.controller.rest;

import com.nhnacademy.springjpa.domain.request.FamilyRelationshipPostRequest;
import com.nhnacademy.springjpa.domain.request.FamilyRelationshipPutRequest;
import com.nhnacademy.springjpa.service.FamilyRelationshipService;
import javax.validation.Valid;
import javax.validation.ValidationException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/residents")
public class RestFamilyResidentController {
  private final FamilyRelationshipService familyRelationshipService;

  public RestFamilyResidentController(FamilyRelationshipService familyRelationshipService) {
    this.familyRelationshipService = familyRelationshipService;
  }

  @PostMapping("/{serialNumber}/relationship")
  public void createFamilyResidentRelationship(@PathVariable(name = "serialNumber") int serialNumber, @Valid @RequestBody FamilyRelationshipPostRequest request, BindingResult bindingResult) {
    if(bindingResult.hasErrors())
      throw new ValidationException("유효성 검증에 실패하였습니다.");

    familyRelationshipService.saveRelationship(serialNumber, request);
  }

  @PutMapping("/{serialNumber}/relationship/{familySerialNumber}")
  public void updateFamilyResidentRelationship(@PathVariable(name = "serialNumber") int serialNumber,
      @PathVariable(name = "familySerialNumber") int familySerialNumber, @Valid @RequestBody FamilyRelationshipPutRequest request, BindingResult bindingResult) {
    if(bindingResult.hasErrors())
      throw new ValidationException("유효성 검증에 실패하였습니다.");

    familyRelationshipService.updateRelationship(serialNumber, familySerialNumber, request);
  }

  @DeleteMapping("/{serialNumber}/relationship/{familySerialNumber}")
  public void deleteFamilyResidentRelationship(@PathVariable(name = "serialNumber") int serialNumber,
      @PathVariable(name = "familySerialNumber") int familySerialNumber) {
    familyRelationshipService.deleteRelationship(serialNumber, familySerialNumber);
  }
}
