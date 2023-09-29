package com.like.hrm.staff.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.like.hrm.staff.application.port.dto.StaffSchoolCareerSaveDTO;
import com.like.hrm.staff.application.port.in.StaffSchoolCareerSelectUseCase;
import com.like.hrm.staff.application.port.out.StaffCommandDbPort;
import com.like.hrm.staff.domain.model.Staff;

@Service
public class StaffSchoolCareerSelectService implements StaffSchoolCareerSelectUseCase {

	StaffCommandDbPort dbPort;
	
	StaffSchoolCareerSelectService(StaffCommandDbPort dbPort) {
		this.dbPort = dbPort;
	}
	
	@Override
	public List<StaffSchoolCareerSaveDTO> select(String organizationCode, String staffNo) {
		return this.dbPort.select(organizationCode, staffNo)
						  .getSchoolCareerList()
						  .getStream()
						  .map(e -> StaffSchoolCareerSaveDTO.toDTO(e))
						  .toList();
	}

	@Override
	public StaffSchoolCareerSaveDTO select(String organizationCode, String staffNo, Long seq) {
		Staff staff = dbPort.select(organizationCode, staffNo);
		
		return StaffSchoolCareerSaveDTO.toDTO(staff.getSchoolCareerList().get(staff, seq));

	}

}
