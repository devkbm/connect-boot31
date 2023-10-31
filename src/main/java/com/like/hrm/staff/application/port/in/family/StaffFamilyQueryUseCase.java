package com.like.hrm.staff.application.port.in.family;

import java.util.List;

import com.like.hrm.staff.application.port.dto.StaffFamilySaveDTO;

public interface StaffFamilyQueryUseCase {
	List<StaffFamilySaveDTO> select(String organizationCode, String staffNo);
}
