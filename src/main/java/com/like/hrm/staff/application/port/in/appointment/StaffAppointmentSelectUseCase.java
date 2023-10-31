package com.like.hrm.staff.application.port.in.appointment;

import com.like.hrm.staff.application.port.dto.StaffAppointmentRecordDTO;

public interface StaffAppointmentSelectUseCase {
	StaffAppointmentRecordDTO select(String organizationCode, String staffNo, Long seq);	
}
