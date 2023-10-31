package com.like.hrm.staff.application.port.in.schoolcareer;

import java.util.List;

import com.like.hrm.staff.application.port.dto.StaffSchoolCareerSaveDTO;

public interface StaffSchoolCareerQueryUseCase {
	List<StaffSchoolCareerSaveDTO> select(String organizationCode, String staffNo);
}
