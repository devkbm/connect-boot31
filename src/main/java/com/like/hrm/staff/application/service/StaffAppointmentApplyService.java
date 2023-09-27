package com.like.hrm.staff.application.service;

import org.springframework.stereotype.Service;

import com.like.hrm.staff.application.port.in.StaffAppointmentApplyUseCase;
import com.like.hrm.staff.application.port.out.StaffCommandDbPort;
import com.like.hrm.staff.domain.model.Staff;
import com.like.hrm.staff.domain.model.appointment.AppointmentRecord;

@Service
public class StaffAppointmentApplyService implements StaffAppointmentApplyUseCase {

	StaffCommandDbPort dbPort;
	
	StaffAppointmentApplyService(StaffCommandDbPort dbPort) {
		this.dbPort = dbPort;
	}
	
	@Override
	public void apply(String organizationCode, String staffNo, Long seq) {
		Staff staff = dbPort.select(organizationCode, staffNo);
		AppointmentRecord entity = staff.getAppointmentRecordList().get(staff, seq);
		
		staff.applyAppointmentRecord(entity);
	}

}
