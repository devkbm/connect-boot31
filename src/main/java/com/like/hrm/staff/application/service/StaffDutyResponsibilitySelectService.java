package com.like.hrm.staff.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.like.hrm.staff.application.port.dto.ResponseStaffDutyResponsibility;
import com.like.hrm.staff.application.port.dto.StaffDutyResponsibilityDTO;
import com.like.hrm.staff.application.port.in.StaffDutyResponsibilitySelectUseCase;
import com.like.hrm.staff.application.port.out.StaffCommandDbPort;
import com.like.hrm.staff.domain.model.Staff;
import com.like.hrm.staff.domain.model.StaffQueryRepository;
import com.like.hrm.staff.domain.model.dutyresponsibility.StaffDuty;

@Service
public class StaffDutyResponsibilitySelectService implements StaffDutyResponsibilitySelectUseCase {

	StaffCommandDbPort dbPort;
	StaffQueryRepository queryRepository;
	
	StaffDutyResponsibilitySelectService(StaffCommandDbPort dbPort
										,StaffQueryRepository queryRepository) {
		this.dbPort = dbPort;
		this.queryRepository = queryRepository;
	}

	@Override
	public List<ResponseStaffDutyResponsibility> select(String organizationCode, String staffNo) {
		return this.queryRepository.getStaffDutyResponsibility(organizationCode, staffNo);
	}

	@Override
	public StaffDutyResponsibilityDTO select(String organizationCode, String staffNo, Long seq) {
		Staff staff = this.dbPort.select(organizationCode, staffNo);		
		StaffDuty entity = staff.getStaffDutyResponsibilityList().get(staff, seq);
				
		return StaffDutyResponsibilityDTO.toDTO(entity);
	}
	
	
}
