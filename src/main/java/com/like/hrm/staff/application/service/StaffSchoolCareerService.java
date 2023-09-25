package com.like.hrm.staff.application.service;

import java.util.List;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.staff.adapter.out.persistence.jpa.repository.StaffJpaRepository;
import com.like.hrm.staff.application.port.dto.SchoolCareerDTO;
import com.like.hrm.staff.domain.model.Staff;
import com.like.hrm.staff.domain.model.StaffId;
import com.like.hrm.staff.domain.model.schoolcareer.StaffSchoolCareer;


@Transactional
@Service
public class StaffSchoolCareerService {

	private StaffJpaRepository repository;
				
	public StaffSchoolCareerService(StaffJpaRepository repository) {
		this.repository = repository;	
	}
	
	public List<StaffSchoolCareer> getSchoolCareerList(String organizationCode, String staffNo) {		
		return findStaff(organizationCode, staffNo).getSchoolCareerList().getStream().toList(); 
	}
	
	public StaffSchoolCareer getSchoolCareer(String organizationCode, String staffNo, Long seq) {
		Staff staff = findStaff(organizationCode, staffNo);
		
		return staff.getSchoolCareerList().get(staff, seq);
	}
	
	public void saveSchoolCareer(SchoolCareerDTO.Form dto) {
		Staff staff = findStaff(dto.organizationCode(), dto.staffNo());	
		StaffSchoolCareer education = staff.getSchoolCareerList().get(staff, dto.seq());
		
		if (education == null) {
			education = dto.newEntity(staff);
		} else {
			dto.modifyEnity(education);
		}
		
		staff.getSchoolCareerList().add(education);
		
		repository.save(staff);
	}
	
	public void deleteSchoolCareer(String organizationCode, String staffNo, Long seq) {
		Staff staff = findStaff(organizationCode, staffNo);
		staff.getSchoolCareerList().remove(staff, seq);
	}
	
	private Staff findStaff(String organizationCode, String staffNo) {
		return repository.findById(new StaffId(organizationCode, staffNo))
				 .orElseThrow(() -> new EntityNotFoundException(staffNo + " 직원번호가 존재하지 않습니다."));
	}
}
