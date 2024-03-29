package com.nhnacademy.springjpa.domain.dto.residentregister;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ResidentRegisterCertificateDTO {
  private final CertificateIssueDTO certificateIssue;
  private final HouseholdResidentInfoDTO householdResidentInfo;
  private final List<HouseMovementAddressLogDTO> houseMovementAddressLogList;
  private final List<HouseholdCompositionResidentDTO> householdCompositionResidentList;
}
