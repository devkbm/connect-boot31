package com.like.hrm.staff.application.service.family;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.staff.application.port.dto.StaffFamilySaveDTO;
import com.like.hrm.staff.application.port.in.family.StaffFamilyQueryUseCase;
import com.like.hrm.staff.application.port.out.StaffCommandDbPort;
import com.like.hrm.staff.domain.model.Staff;

@Transactional(readOnly = true)
@Service
public class StaffFamilyQueryService implements StaffFamilyQueryUseCase {

	StaffCommandDbPort dbPort;
	
	StaffFamilyQueryService(StaffCommandDbPort dbPort) {
		this.dbPort = dbPort;
	}

	@Override
	public List<StaffFamilySaveDTO> select(String organizationCode, String staffNo) {
		Staff staff = dbPort.select(organizationCode, staffNo);
		
		return staff.getFamilyList()
					.getStream()
					.map(e -> StaffFamilySaveDTO.toDTO(e))
					.toList();
	}
}
