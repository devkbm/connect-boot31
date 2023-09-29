package com.like.hrm.staff.application.port.in;

import com.like.hrm.staff.application.port.dto.StaffCreateDTO;

public interface StaffCreateUseCase {
	void create(StaffCreateDTO dto);
}
