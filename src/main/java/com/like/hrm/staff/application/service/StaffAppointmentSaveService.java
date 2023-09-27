package com.like.hrm.staff.application.service;

import org.springframework.stereotype.Service;

import com.like.hrm.staff.application.port.dto.StaffAppointmentRecordDTO;
import com.like.hrm.staff.application.port.in.StaffAppointmentSaveUseCase;
import com.like.hrm.staff.application.port.out.StaffCommandDbPort;
import com.like.hrm.staff.domain.model.Staff;
import com.like.hrm.staff.domain.model.appointment.AppointmentRecord;

@Service
public class StaffAppointmentSaveService implements StaffAppointmentSaveUseCase {

	StaffCommandDbPort dbPort;
	
	StaffAppointmentSaveService(StaffCommandDbPort dbPort) {
		this.dbPort = dbPort;
	}
	
	@Override
	public void save(StaffAppointmentRecordDTO dto) {		
		
		Staff staff = dbPort.select(dto.organizationCode(), dto.staffNo());		
		AppointmentRecord entity = staff.getAppointmentRecordList().get(staff, dto.seq());
		
		if (entity == null) {
			entity = dto.newEntity(staff);
		} else {
			dto.modifyEntity(entity);
		}
		
		staff.getAppointmentRecordList().add(entity);		
		staff.applyAppointmentRecord(entity);		 
		
		dbPort.save(staff);
	}
	
}
