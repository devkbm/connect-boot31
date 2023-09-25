package com.like.hrm.staff.application.service;

import jakarta.persistence.EntityExistsException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.staff.application.port.dto.FormStaff;
import com.like.hrm.staff.application.port.dto.NewStaff;
import com.like.hrm.staff.domain.model.Staff;
import com.like.hrm.staff.domain.model.StaffId;
import com.like.hrm.staff.domain.model.StaffName;
import com.like.hrm.staff.domain.model.StaffNoCreateStrategy;
import com.like.hrm.staff.domain.model.StaffRepository;

@Service
@Transactional
public class StaffService {
	
	private StaffRepository repository;	
		
	public StaffService(StaffRepository repository) {
		this.repository = repository;
	}	
	
	public Staff getStaff(String organizationCode, String staffNo) {
		return repository.findById(new StaffId(organizationCode, staffNo)).orElse(null);
	}
	
	public void saveStaff(Staff staff) {				
		repository.save(staff);
	}
	
	public void saveStaff(FormStaff dto) {
		Staff staff = this.getStaff(dto.organizationCode(), dto.staffNo());
		
		dto.modifyEntity(staff);
		
		repository.save(staff);
	}
	
	public void newStaff(NewStaff dto) {		
		if (isExistStaff(dto.organizationCode(), dto.staffNo())) throw new EntityExistsException("동일 직원번호가 존재합니다 : " + dto.getStaffId());

		StaffNoCreateStrategy strategy = () -> dto.staffNo();
		
		Staff staff = new Staff(dto.organizationCode()
				               ,strategy
				               ,new StaffName(dto.name(), dto.nameEng(), dto.nameEng())
				               ,dto.residentRegistrationNumber());
									
		repository.save(staff);
	}
	
	public void deleteStaff(String organizationCode, String staffNo) {		
		repository.deleteById(new StaffId(organizationCode, staffNo));
	}
		
	private boolean isExistStaff(String organizationCode, String staffNo) {
		return repository.findById(new StaffId(organizationCode, staffNo)).isPresent();
	}
		
}
