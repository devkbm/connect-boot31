package com.like.hrm.staff.application.service;

import org.springframework.stereotype.Service;

import com.like.hrm.staff.application.port.dto.ResponseStaff;
import com.like.hrm.staff.application.port.in.StaffSelectUseCase;
import com.like.hrm.staff.application.port.out.StaffSelectDbPort;

@Service
public class StaffSelectService implements StaffSelectUseCase {

	StaffSelectDbPort dbPort;
	
	StaffSelectService(StaffSelectDbPort dbPort) {
		this.dbPort = dbPort;
	}
	
	@Override
	public ResponseStaff select(String organizationCode, String id) {
		return this.dbPort.select(organizationCode, id);
	}
	
}
