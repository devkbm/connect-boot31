package com.like.hrm.staff.application.port.in;

import java.util.List;

import com.like.hrm.staff.application.port.dto.StaffAppointmentRecordDTO;

public interface StaffAppointmentSelectUseCase {
	StaffAppointmentRecordDTO select(String organizationCode, String staffNo, Long seq);
	
	List<StaffAppointmentRecordDTO> select(String organizationCode, String staffNo);
}
