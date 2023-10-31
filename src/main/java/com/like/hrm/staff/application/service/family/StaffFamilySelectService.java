package com.like.hrm.staff.application.service.family;

import org.springframework.stereotype.Service;

import com.like.hrm.staff.application.port.dto.StaffFamilySaveDTO;
import com.like.hrm.staff.application.port.in.family.StaffFamilySelectUseCase;
import com.like.hrm.staff.application.port.out.StaffCommandDbPort;
import com.like.hrm.staff.domain.model.Staff;

@Service
public class StaffFamilySelectService implements StaffFamilySelectUseCase {

	StaffCommandDbPort dbPort;
	
	StaffFamilySelectService(StaffCommandDbPort dbPort) {
		this.dbPort = dbPort;
	}

	@Override
	public StaffFamilySaveDTO select(String organizationCode, String staffNo, Long seq) {
		Staff staff = dbPort.select(organizationCode, staffNo);
		return StaffFamilySaveDTO.toDTO(staff.getFamilyList().get(staff, seq));
	}
}
