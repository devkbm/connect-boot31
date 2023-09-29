package com.like.hrm.staff.application.service;

import org.springframework.stereotype.Service;

import com.like.hrm.staff.application.port.dto.StaffCreateDTO;
import com.like.hrm.staff.application.port.in.StaffCreateUseCase;
import com.like.hrm.staff.application.port.out.StaffCommandDbPort;
import com.like.hrm.staff.domain.model.Staff;
import com.like.hrm.staff.domain.model.StaffName;
import com.like.hrm.staff.domain.model.StaffNoCreateStrategy;

import jakarta.persistence.EntityExistsException;

@Service
public class StaffCreateService implements StaffCreateUseCase {

	StaffCommandDbPort dbPort;
	
	StaffCreateService(StaffCommandDbPort dbPort) {
		this.dbPort = dbPort;
	}
	
	@Override
	public void create(StaffCreateDTO dto) {
		if (isExistStaff(dto.organizationCode(), dto.staffNo())) throw new EntityExistsException("동일 직원번호가 존재합니다 : " + dto.getStaffId());

		StaffNoCreateStrategy strategy = () -> dto.staffNo();
		
		Staff staff = new Staff(dto.organizationCode()
				               ,strategy
				               ,new StaffName(dto.name(), dto.nameEng(), dto.nameEng())
				               ,dto.residentRegistrationNumber());
									
		dbPort.save(staff);
	}
	
	private boolean isExistStaff(String organizationCode, String staffNo) {
		return dbPort.select(organizationCode, staffNo) == null ? false : true;
	}

}
