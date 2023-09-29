package com.like.hrm.staff.application.port.in;

public interface StaffSchoolCareerDeleteUseCase {
	void delete(String organizationCode, String staffNo, Long seq);
}
