package com.like.hrm.staff.application.port.out;

import com.like.hrm.staff.application.port.dto.ResponseStaff;

public interface StaffSelectDbPort {
	ResponseStaff select(String organizationCode, String id);
}
