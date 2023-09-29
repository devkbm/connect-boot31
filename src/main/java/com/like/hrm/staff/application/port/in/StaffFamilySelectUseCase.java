package com.like.hrm.staff.application.port.in;

import java.util.List;

import com.like.hrm.staff.application.port.dto.StaffFamilySaveDTO;

public interface StaffFamilySelectUseCase {

	List<StaffFamilySaveDTO> select(String organizationCode, String staffNo);
	
	StaffFamilySaveDTO select(String organizationCode, String staffNo, Long seq);
}

