package com.like.hrm.staff.application.service.license;

import org.springframework.stereotype.Service;

import com.like.hrm.staff.application.port.in.license.StaffLicenseDeleteUseCase;
import com.like.hrm.staff.application.port.out.StaffCommandDbPort;
import com.like.hrm.staff.domain.model.Staff;

@Service
public class StaffLicenseDeleteService implements StaffLicenseDeleteUseCase {

	StaffCommandDbPort dbPort;
	
	StaffLicenseDeleteService(StaffCommandDbPort dbPort) {
		this.dbPort = dbPort;
	}
	
	@Override
	public void delete(String organizationCode, String staffNo, Long seq) {
		Staff staff = this.dbPort.select(organizationCode, staffNo);
		
		staff.getLicenseList().remove(staff, seq);
		
		this.dbPort.save(staff);
	}

}
