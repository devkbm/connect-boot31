package com.like.hrm.staff.application.port.in.schoolcareer;

public interface StaffSchoolCareerDeleteUseCase {
	void delete(String organizationCode, String staffNo, Long seq);
}
