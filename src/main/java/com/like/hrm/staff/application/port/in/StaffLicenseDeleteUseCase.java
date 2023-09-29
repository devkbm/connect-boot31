package com.like.hrm.staff.application.port.in;

public interface StaffLicenseDeleteUseCase {
	void delete(String organizationCode, String staffNo, Long seq);
}
