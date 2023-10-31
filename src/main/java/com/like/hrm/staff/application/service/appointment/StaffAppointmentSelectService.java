package com.like.hrm.staff.application.service.appointment;

import org.springframework.stereotype.Service;

import com.like.hrm.staff.application.port.dto.StaffAppointmentRecordDTO;
import com.like.hrm.staff.application.port.in.appointment.StaffAppointmentSelectUseCase;
import com.like.hrm.staff.application.port.out.StaffAppointmentSelectDbPort;

@Service
public class StaffAppointmentSelectService implements StaffAppointmentSelectUseCase {

	StaffAppointmentSelectDbPort dbPort;
	
	StaffAppointmentSelectService(StaffAppointmentSelectDbPort dbPort) {
		this.dbPort = dbPort;
	}
	
	@Override
	public StaffAppointmentRecordDTO select(String organizationCode, String staffNo, Long seq) {
		return StaffAppointmentRecordDTO.convert(this.dbPort.select(organizationCode, staffNo, seq));
	}

	

}
