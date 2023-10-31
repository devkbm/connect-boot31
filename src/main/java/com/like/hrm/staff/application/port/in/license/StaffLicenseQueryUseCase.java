package com.like.hrm.staff.application.port.in.license;

import java.util.List;

import com.like.hrm.staff.application.port.dto.StaffLicenseSaveDTO;

public interface StaffLicenseQueryUseCase {
	List<StaffLicenseSaveDTO> select(String organizationCode, String staffNo);
}
