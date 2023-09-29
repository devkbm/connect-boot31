package com.like.hrm.staff.application.port.in;

import java.util.List;

import com.like.hrm.staff.application.port.dto.StaffSchoolCareerSaveDTO;

public interface StaffSchoolCareerSelectUseCase {
	List<StaffSchoolCareerSaveDTO> select(String organizationCode, String staffNo);
	
	StaffSchoolCareerSaveDTO select(String organizationCode, String staffNo, Long seq);
}
