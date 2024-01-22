package com.nhnacademy.springjpa.service.Impl;

import com.nhnacademy.springjpa.domain.request.FamilyRelationshipPostRequest;
import com.nhnacademy.springjpa.domain.request.FamilyRelationshipPutRequest;
import com.nhnacademy.springjpa.entity.FamilyRelationship;
import com.nhnacademy.springjpa.entity.FamilyRelationship.PK;
import com.nhnacademy.springjpa.entity.Resident;
import com.nhnacademy.springjpa.exception.FamilyRelationshipNotFoundException;
import com.nhnacademy.springjpa.exception.ResidentNotFoundException;
import com.nhnacademy.springjpa.repository.FamilyRelationshipRepository;
import com.nhnacademy.springjpa.repository.ResidentRepository;
import com.nhnacademy.springjpa.service.FamilyRelationshipService;
import java.util.HashMap;
import java.util.Map;
import javax.validation.ValidationException;
import org.springframework.stereotype.Service;

@Service
public class FamilyRelationshipServiceImpl implements FamilyRelationshipService {
  private final FamilyRelationshipRepository familyRelationshipRepository;
  private final ResidentRepository residentRepository;

  public FamilyRelationshipServiceImpl(FamilyRelationshipRepository familyRelationshipRepository,
      ResidentRepository residentRepository) {
    this.familyRelationshipRepository = familyRelationshipRepository;
    this.residentRepository = residentRepository;
  }

  public static Map<String, String> relationshipMap = Map.of(
      "father", "부",
      "mother", "모",
      "spouse", "배우자",
      "child", "자녀"
  );
  public void saveRelationship(int serialNumber, FamilyRelationshipPostRequest request) {
    if(!residentRepository.existsById(serialNumber) || !residentRepository.existsById(request.getFamilySerialNumber()))
      throw new ResidentNotFoundException("주민 일련번호가 존재하지 않습니다");
    if(familyRelationshipRepository.existsById(new PK(request.getFamilySerialNumber(), serialNumber))) {
      throw new FamilyRelationshipNotFoundException("이미 존재하는 가족 관계입니다");
    }

    Resident baseResident = residentRepository.findById(serialNumber).get();
    Resident familyResident = residentRepository.findById(request.getFamilySerialNumber()).get();

    if(!relationshipMap.containsKey(request.getRelationShip())) {
      throw new ValidationException("잘못된 값을 입력하셨습니다");
    }
    familyRelationshipRepository.save(new FamilyRelationship(baseResident,
        familyResident, relationshipMap.get(request.getRelationShip())));
  }

  public void updateRelationship(int serialNumber, int familySerialNumber, FamilyRelationshipPutRequest request) {
    if(!residentRepository.existsById(serialNumber) || !residentRepository.existsById(familySerialNumber))
      throw new ResidentNotFoundException("주민 일련번호가 존재하지 않습니다");

    Resident baseResident = residentRepository.findById(serialNumber).get();
    Resident familyResident = residentRepository.findById(familySerialNumber).get();

    if(!relationshipMap.containsKey(request.getRelationShip())) {
      throw new ValidationException("잘못된 값을 입력하셨습니다");
    }
    if(!familyRelationshipRepository.existsById(new PK(familySerialNumber, serialNumber))) {
      throw new FamilyRelationshipNotFoundException("존재하지 않는 가족 관계입니다");
    }
    familyRelationshipRepository.save(new FamilyRelationship(baseResident,
        familyResident, relationshipMap.get(request.getRelationShip())));
  }

  public void deleteRelationship(int serialNumber, int familySerialNumber) {
    if(!residentRepository.existsById(serialNumber) || !residentRepository.existsById(familySerialNumber))
      throw new ResidentNotFoundException("주민 일련번호가 존재하지 않습니다");

    FamilyRelationship.PK pk = new FamilyRelationship.PK(familySerialNumber, serialNumber);
    if(!familyRelationshipRepository.existsById(pk)) {
      throw new FamilyRelationshipNotFoundException("존재하지 않는 가족 관계입니다");
    }
    familyRelationshipRepository.deleteById(pk);
  }
}
