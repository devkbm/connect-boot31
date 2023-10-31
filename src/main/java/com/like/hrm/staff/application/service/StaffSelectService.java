package com.like.hrm.staff.application.service;

import org.springframework.stereotype.Service;

import com.like.hrm.staff.application.port.dto.ResponseStaff;
import com.like.hrm.staff.application.port.in.StaffSelectUseCase;
import com.like.hrm.staff.application.port.out.StaffCommandDbPort;

@Service
public class StaffSelectService implements StaffSelectUseCase {

	StaffCommandDbPort dbPort;
	
	StaffSelectService(StaffCommandDbPort dbPort) {
		this.dbPort = dbPort;
	}
	
	@Override
	public ResponseStaff select(String organizationCode, String staffNo) {
		return ResponseStaff.toDTO(this.dbPort.select(organizationCode, staffNo));
	}
	
}
