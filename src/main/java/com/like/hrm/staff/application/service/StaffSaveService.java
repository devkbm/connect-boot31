package com.like.hrm.staff.application.service;

import org.springframework.stereotype.Service;

import com.like.hrm.staff.application.port.dto.FormStaff;
import com.like.hrm.staff.application.port.in.StaffSaveUseCase;
import com.like.hrm.staff.application.port.out.StaffCommandDbPort;
import com.like.hrm.staff.domain.model.Staff;

@Service
public class StaffSaveService implements StaffSaveUseCase {

	StaffCommandDbPort dbPort;
	
	StaffSaveService(StaffCommandDbPort dbPort) {
		this.dbPort = dbPort;
	}
	
	@Override
	public void save(FormStaff dto) {
		Staff staff = this.dbPort.select(dto.organizationCode(), dto.staffNo());
		
		dto.modifyEntity(staff);
		
		dbPort.save(staff);		
	}

}
