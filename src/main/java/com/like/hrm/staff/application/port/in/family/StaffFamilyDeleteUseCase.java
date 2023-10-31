package com.like.hrm.staff.application.port.in.family;

public interface StaffFamilyDeleteUseCase {
	void delete(String organizationCode, String staffNo, Long seq);
}
