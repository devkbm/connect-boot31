package com.like.hrm.staff.application.service;

import org.springframework.stereotype.Service;

import com.like.hrm.staff.application.port.in.StaffFamilyDeleteUseCase;
import com.like.hrm.staff.application.port.out.StaffCommandDbPort;
import com.like.hrm.staff.domain.model.Staff;

@Service
public class StaffFamilyDeleteService implements StaffFamilyDeleteUseCase {

	StaffCommandDbPort dbPort;
	
	StaffFamilyDeleteService(StaffCommandDbPort dbPort) {
		this.dbPort = dbPort;
	}
	
	@Override
	public void delete(String organizationCode, String staffNo, Long seq) {
		Staff staff = this.dbPort.select(organizationCode, staffNo);
		
		staff.getFamilyList().remove(staff, seq);
		
		this.dbPort.save(staff);
	}

}
