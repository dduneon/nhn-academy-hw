package com.nhnacademy.springjpa.service;

import com.nhnacademy.springjpa.domain.request.FamilyRelationshipPostRequest;
import com.nhnacademy.springjpa.domain.request.FamilyRelationshipPutRequest;

public interface FamilyRelationshipService {
  void saveRelationship(int serialNumber, FamilyRelationshipPostRequest request);
  void updateRelationship(int serialNumber, int familySerialNumber, FamilyRelationshipPutRequest request);
  void deleteRelationship(int serialNumber, int familySerialNumber);

}
