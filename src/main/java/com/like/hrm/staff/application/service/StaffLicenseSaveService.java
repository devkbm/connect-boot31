package com.like.hrm.staff.application.service;

import org.springframework.stereotype.Service;

import com.like.hrm.staff.application.port.dto.StaffLicenseSaveDTO;
import com.like.hrm.staff.application.port.in.StaffLicenseSaveUseCase;
import com.like.hrm.staff.application.port.out.StaffCommandDbPort;
import com.like.hrm.staff.domain.model.Staff;
import com.like.hrm.staff.domain.model.license.StaffLicense;

@Service
public class StaffLicenseSaveService implements StaffLicenseSaveUseCase {

	StaffCommandDbPort dbPort;
	
	StaffLicenseSaveService(StaffCommandDbPort dbPort) {
		this.dbPort = dbPort;
	}
	
	@Override
	public void save(StaffLicenseSaveDTO dto) {
		Staff staff = this.dbPort.select(dto.organizationCode(), dto.staffNo());		
		StaffLicense license = staff.getLicenseList().get(staff, dto.seq());
		
		if (license == null) {
			license = dto.newEntity(staff);
		} else {
			dto.modifyEntity(license);
		}
		
		staff.getLicenseList().add(license);
		
		dbPort.save(staff);
	}

}
