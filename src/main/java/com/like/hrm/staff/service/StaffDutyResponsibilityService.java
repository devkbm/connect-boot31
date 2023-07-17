package com.like.hrm.staff.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.staff.boundary.StaffDutyResponsibilityDTO;
import com.like.hrm.staff.domain.model.Staff;
import com.like.hrm.staff.domain.model.StaffId;
import com.like.hrm.staff.domain.model.StaffRepository;
import com.like.hrm.staff.domain.model.dutyresponsibility.StaffDuty;

@Transactional
@Service
public class StaffDutyResponsibilityService {

	private StaffRepository repository;
	
	public StaffDutyResponsibilityService(StaffRepository repository) {
		this.repository = repository;
	}
	
	public List<StaffDuty> getList(String organizationCode, String staffNo) {
		Staff staff = this.repository.findById(new StaffId(organizationCode, staffNo)).orElse(null);
		
		return staff.getStaffDutyResponsibilityList().stream().toList();
	}
	
	public StaffDuty get(String organizationCode, String staffNo, Long seq) {
		Staff staff = this.repository.findById(new StaffId(organizationCode, staffNo)).orElse(null);		
		StaffDuty entity = staff.getStaffDutyResponsibilityList().get(staff, seq);
		
		return entity;
	}
	
	public void save(StaffDutyResponsibilityDTO.Form dto) {
		Staff staff = this.repository.findById(new StaffId(dto.organizationCode(), dto.staffNo())).orElse(null);		
		StaffDuty entity = staff.getStaffDutyResponsibilityList().get(staff, dto.seq());
		
		if (entity == null) {
			entity = dto.newEntity(staff);
		} else {
			dto.modifyEntity(entity);
		}
		
		this.repository.save(staff);
	}
	
	
}
