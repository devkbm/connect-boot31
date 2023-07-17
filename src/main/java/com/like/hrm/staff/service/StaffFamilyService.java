package com.like.hrm.staff.service;

import java.util.List;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.staff.boundary.FamilyDTO;
import com.like.hrm.staff.domain.model.Staff;
import com.like.hrm.staff.domain.model.StaffId;
import com.like.hrm.staff.domain.model.StaffRepository;
import com.like.hrm.staff.domain.model.family.StaffFamily;

@Transactional
@Service
public class StaffFamilyService {

	private StaffRepository repository;
	
	public StaffFamilyService(StaffRepository repository) {
		this.repository = repository;
	}
	
	public List<StaffFamily> getFamilyList(String organizationCode, String staffNo) {
		Staff staff = findStaff(organizationCode, staffNo);
		
		return staff.getFamilyList().getStream().toList();
	}
	
	public StaffFamily getFamily(String organizationCode, String staffNo, Long seq) {
		Staff staff = findStaff(organizationCode, staffNo);
						
		return staff.getFamilyList().get(staff, seq);
	}
	
	public void saveFamily(FamilyDTO.Form dto) {
		Staff staff = findStaff(dto.organizationCode(), dto.staffNo());		
		StaffFamily entity = staff.getFamilyList().get(staff, dto.seq());
		
		if (entity == null) {
			entity = dto.newEntity(staff);
		} else {
			dto.modifyEntity(entity);
		}
		
		staff.getFamilyList().add(entity);
		
		repository.save(staff);
	}
	
	public void deleteFamily(String organizationCode, String staffNo, Long seq) {
		Staff staff = findStaff(organizationCode, staffNo);
		
		staff.getFamilyList().remove(staff, seq);
	}
	
	private Staff findStaff(String organizationCode, String staffNo) {
		return repository.findById(new StaffId(organizationCode, staffNo))
						 .orElseThrow(() -> new EntityNotFoundException(staffNo + " 직원번호가 존재하지 않습니다."));
	}
}
