package com.like.hrm.staff.application.port.in;

import java.util.List;

import com.like.hrm.staff.application.port.dto.StaffLicenseSaveDTO;

public interface StaffLicenseSelectUseCase {
	List<StaffLicenseSaveDTO> select(String organizationCode, String staffNo);
	
	StaffLicenseSaveDTO select(String organizationCode, String staffNo, Long seq);
}
