package com.like.hrm.staff.application.port.in.appointment;

import java.util.List;

import com.like.hrm.staff.application.port.dto.StaffAppointmentRecordDTO;

public interface StaffAppointmentQueryUseCase {
	List<StaffAppointmentRecordDTO> select(String organizationCode, String staffNo);
}
