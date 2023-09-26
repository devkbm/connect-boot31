package com.like.hrm.staff.application.port.out;

import java.util.List;

import com.like.hrm.staff.application.port.dto.StaffAppointmentRecordDTO;

public interface StaffAppointmentSelectDbPort {
	StaffAppointmentRecordDTO select(String organizationCode, String staffNo, Long seq);
	
	List<StaffAppointmentRecordDTO> select(String organizationCode, String staffNo);
}
