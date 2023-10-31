package com.like.hrm.staff.application.port.in.appointment;

import com.like.hrm.staff.application.port.dto.StaffAppointmentRecordDTO;

public interface StaffAppointmentSaveUseCase {
	void save(StaffAppointmentRecordDTO dto);	
}
