package com.like.hrm.staff.application.service.schoolcareer;

import org.springframework.stereotype.Service;

import com.like.hrm.staff.application.port.in.schoolcareer.StaffSchoolCareerDeleteUseCase;
import com.like.hrm.staff.application.port.out.StaffCommandDbPort;
import com.like.hrm.staff.domain.model.Staff;

@Service
public class StaffSChoolCareerDeleteService implements StaffSchoolCareerDeleteUseCase {

	StaffCommandDbPort dbPort;
	
	StaffSChoolCareerDeleteService(StaffCommandDbPort dbPort) {
		this.dbPort = dbPort;
	}
	
	@Override
	public void delete(String organizationCode, String staffNo, Long seq) {
		Staff staff = dbPort.select(organizationCode, staffNo);
		staff.getSchoolCareerList().remove(staff, seq);
		
		this.dbPort.save(staff);
	}

}
