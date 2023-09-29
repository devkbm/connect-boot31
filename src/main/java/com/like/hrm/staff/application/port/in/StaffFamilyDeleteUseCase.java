package com.like.hrm.staff.application.port.in;

public interface StaffFamilyDeleteUseCase {
	void delete(String organizationCode, String staffNo, Long seq);
}
