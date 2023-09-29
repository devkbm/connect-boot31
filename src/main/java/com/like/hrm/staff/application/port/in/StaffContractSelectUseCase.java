package com.like.hrm.staff.application.port.in;

import com.like.hrm.staff.application.port.dto.StaffContactSaveDTO;

public interface StaffContractSelectUseCase {

	StaffContactSaveDTO select(String organizationCode, String staffNo);
}
