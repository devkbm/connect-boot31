package com.like.hrm.staff.application.service.dutyresponsibility;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.staff.application.port.dto.ResponseStaffDutyResponsibility;
import com.like.hrm.staff.application.port.in.dutyresponsibility.StaffDutyResponsibilityQueryUseCase;
import com.like.hrm.staff.domain.model.StaffQueryRepository;

@Transactional(readOnly = true)
@Service
public class StaffDutyResponsibilityQueryService implements StaffDutyResponsibilityQueryUseCase {

	StaffQueryRepository dbPort;
	
	StaffDutyResponsibilityQueryService(StaffQueryRepository dbPort) {
		this.dbPort = dbPort;
	}

	@Override
	public List<ResponseStaffDutyResponsibility> select(String organizationCode, String staffNo) {
		return this.dbPort.getStaffDutyResponsibility(organizationCode, staffNo);
	}
}
