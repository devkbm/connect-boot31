package com.like.hrm.staff.application.port.in;

import java.util.List;

import com.like.hrm.staff.application.port.dto.ResponseStaffDutyResponsibility;
import com.like.hrm.staff.application.port.dto.StaffDutyResponsibilityDTO;

public interface StaffDutyResponsibilitySelectUseCase {

	List<ResponseStaffDutyResponsibility> select(String organizationCode, String staffNo);
	
	StaffDutyResponsibilityDTO select(String organizationCode, String staffNo, Long seq);
}
