package com.like.hrm.staff.application.service;

import org.springframework.stereotype.Service;

import com.like.hrm.staff.application.port.dto.StaffContactSaveDTO;
import com.like.hrm.staff.application.port.in.StaffContractSelectUseCase;
import com.like.hrm.staff.application.port.out.StaffCommandDbPort;
import com.like.hrm.staff.domain.model.Staff;

@Service
public class StaffContractSelectService implements StaffContractSelectUseCase {

	StaffCommandDbPort dbPort;
	
	StaffContractSelectService(StaffCommandDbPort dbPort) {
		this.dbPort = dbPort;
	}

	@Override
	public StaffContactSaveDTO select(String organizationCode, String staffNo) {
		Staff staff = dbPort.select(organizationCode, staffNo);
		
		return StaffContactSaveDTO.toDTO(staff);
	}
}
