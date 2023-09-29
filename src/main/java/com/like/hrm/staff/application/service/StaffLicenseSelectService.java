package com.like.hrm.staff.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.like.hrm.staff.application.port.dto.StaffLicenseSaveDTO;
import com.like.hrm.staff.application.port.in.StaffLicenseSelectUseCase;
import com.like.hrm.staff.application.port.out.StaffCommandDbPort;
import com.like.hrm.staff.domain.model.Staff;

@Service
public class StaffLicenseSelectService implements StaffLicenseSelectUseCase {

	StaffCommandDbPort dbPort;
	
	StaffLicenseSelectService(StaffCommandDbPort dbPort) {
		this.dbPort = dbPort;
	}
	
	@Override
	public List<StaffLicenseSaveDTO> select(String organizationCode, String staffNo) {
		Staff staff = this.dbPort.select(organizationCode, staffNo);
		
		return staff.getLicenseList()
				 	.getStream()
				 	.map(e -> StaffLicenseSaveDTO.toDTO(e))
				 	.toList();
	}

	@Override
	public StaffLicenseSaveDTO select(String organizationCode, String staffNo, Long seq) {
		Staff staff = this.dbPort.select(organizationCode, staffNo);
		return StaffLicenseSaveDTO.toDTO(staff.getLicenseList().get(staff, seq));
	}

}
