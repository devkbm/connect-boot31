package com.like.hrm.staff.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.like.hrm.staff.application.port.dto.StaffFamilySaveDTO;
import com.like.hrm.staff.application.port.in.StaffFamilySelectUseCase;
import com.like.hrm.staff.application.port.out.StaffCommandDbPort;
import com.like.hrm.staff.domain.model.Staff;

@Service
public class StaffFamilySelectService implements StaffFamilySelectUseCase {

	StaffCommandDbPort dbPort;
	
	StaffFamilySelectService(StaffCommandDbPort dbPort) {
		this.dbPort = dbPort;
	}

	@Override
	public List<StaffFamilySaveDTO> select(String organizationCode, String staffNo) {
		Staff staff = dbPort.select(organizationCode, staffNo);
		
		return staff.getFamilyList()
					.getStream()
					.map(e -> StaffFamilySaveDTO.toDTO(e))
					.toList();
	}

	@Override
	public StaffFamilySaveDTO select(String organizationCode, String staffNo, Long seq) {
		Staff staff = dbPort.select(organizationCode, staffNo);
		return StaffFamilySaveDTO.toDTO(staff.getFamilyList().get(staff, seq));
	}
}
