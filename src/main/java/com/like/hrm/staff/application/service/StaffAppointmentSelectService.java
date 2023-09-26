package com.like.hrm.staff.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.like.hrm.staff.application.port.dto.StaffAppointmentRecordDTO;
import com.like.hrm.staff.application.port.in.StaffAppointmentSelectUseCase;
import com.like.hrm.staff.application.port.out.StaffAppointmentSelectDbPort;

@Service
public class StaffAppointmentSelectService implements StaffAppointmentSelectUseCase {

	StaffAppointmentSelectDbPort dbPort;
	
	StaffAppointmentSelectService(StaffAppointmentSelectDbPort dbPort) {
		this.dbPort = dbPort;
	}
	
	@Override
	public StaffAppointmentRecordDTO select(String organizationCode, String staffNo, Long seq) {
		return this.dbPort.select(organizationCode, staffNo, seq);
	}

	@Override
	public List<StaffAppointmentRecordDTO> select(String organizationCode, String staffNo) {
		return this.dbPort.select(organizationCode, staffNo);
	}

}
