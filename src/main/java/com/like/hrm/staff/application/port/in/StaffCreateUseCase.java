package com.like.hrm.staff.application.port.in;

import com.like.hrm.staff.application.port.dto.NewStaff;

public interface StaffCreateUseCase {
	void create(NewStaff dto);
}
