package com.like.hrm.staff.application.service.dutyresponsibility;

import org.springframework.stereotype.Service;

import com.like.hrm.staff.application.port.dto.StaffDutyResponsibilityDTO;
import com.like.hrm.staff.application.port.in.dutyresponsibility.StaffDutyResponsibilitySelectUseCase;
import com.like.hrm.staff.application.port.out.StaffCommandDbPort;
import com.like.hrm.staff.domain.model.Staff;
import com.like.hrm.staff.domain.model.dutyresponsibility.StaffDuty;

@Service
public class StaffDutyResponsibilitySelectService implements StaffDutyResponsibilitySelectUseCase {

	StaffCommandDbPort dbPort;	
	
	StaffDutyResponsibilitySelectService(StaffCommandDbPort dbPort) {
		this.dbPort = dbPort;		
	}

	@Override
	public StaffDutyResponsibilityDTO select(String organizationCode, String staffNo, Long seq) {
		Staff staff = this.dbPort.select(organizationCode, staffNo);		
		StaffDuty entity = staff.getStaffDutyResponsibilityList().get(staff, seq);
				
		return StaffDutyResponsibilityDTO.toDTO(entity);
	}
	
	
}
