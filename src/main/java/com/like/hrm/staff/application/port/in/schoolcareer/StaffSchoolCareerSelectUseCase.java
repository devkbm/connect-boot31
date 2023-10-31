package com.like.hrm.staff.application.port.in.schoolcareer;

import com.like.hrm.staff.application.port.dto.StaffSchoolCareerSaveDTO;

public interface StaffSchoolCareerSelectUseCase {	
	StaffSchoolCareerSaveDTO select(String organizationCode, String staffNo, Long seq);
}
