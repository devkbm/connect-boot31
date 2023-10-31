package com.like.hrm.staff.application.port.in.license;

import com.like.hrm.staff.application.port.dto.StaffLicenseSaveDTO;

public interface StaffLicenseSelectUseCase {	
	StaffLicenseSaveDTO select(String organizationCode, String staffNo, Long seq);
}
