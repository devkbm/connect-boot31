package com.like.hrm.staff.application.service;

import org.springframework.stereotype.Service;

import com.like.hrm.staff.application.port.in.StaffDeleteUseCase;
import com.like.hrm.staff.application.port.out.StaffCommandDbPort;

@Service
public class StaffDeleteService implements StaffDeleteUseCase {

	StaffCommandDbPort dbPort;
	
	StaffDeleteService(StaffCommandDbPort dbPort) {
		this.dbPort = dbPort;
	}
	
	@Override
	public void delete(String organizationCode, String id) {
		this.dbPort.delete(organizationCode, id);		
	}

}
