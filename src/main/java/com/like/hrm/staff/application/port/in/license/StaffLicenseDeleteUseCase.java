package com.like.hrm.staff.application.port.in.license;

public interface StaffLicenseDeleteUseCase {
	void delete(String organizationCode, String staffNo, Long seq);
}
