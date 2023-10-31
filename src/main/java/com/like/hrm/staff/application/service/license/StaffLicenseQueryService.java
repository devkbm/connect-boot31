package com.like.hrm.staff.application.service.license;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.staff.application.port.dto.StaffLicenseSaveDTO;
import com.like.hrm.staff.application.port.in.license.StaffLicenseQueryUseCase;
import com.like.hrm.staff.application.port.out.StaffCommandDbPort;
import com.like.hrm.staff.domain.model.Staff;

@Transactional(readOnly = true)
@Service
public class StaffLicenseQueryService implements StaffLicenseQueryUseCase {

	StaffCommandDbPort dbPort;
	
	StaffLicenseQueryService(StaffCommandDbPort dbPort) {
		this.dbPort = dbPort;
	}
	
	@Override
	public List<StaffLicenseSaveDTO> select(String organizationCode, String staffNo) {
		Staff staff = this.dbPort.select(organizationCode, staffNo);
		
		return staff.getLicenseList()
				 	.getStream()
				 	.map(e -> StaffLicenseSaveDTO.toDTO(e))
				 	.toList();
	}
}
