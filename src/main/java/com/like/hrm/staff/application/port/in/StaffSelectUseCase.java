package com.like.hrm.staff.application.port.in;

import com.like.hrm.staff.application.port.dto.ResponseStaff;

public interface StaffSelectUseCase {
	ResponseStaff select(String organizationCode, String staffNo);
}
