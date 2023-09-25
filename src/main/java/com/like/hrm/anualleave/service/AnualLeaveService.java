package com.like.hrm.anualleave.service;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.anualleave.boundary.AnualLeaveDTO;
import com.like.hrm.anualleave.domain.model.AnualLeave;
import com.like.hrm.anualleave.domain.model.AnualLeaveId;
import com.like.hrm.anualleave.domain.model.AnualLeaveRepository;
import com.like.hrm.staff.adapter.out.persistence.jpa.repository.StaffJpaRepository;
import com.like.hrm.staff.domain.model.Staff;
import com.like.hrm.staff.domain.model.StaffId;

@Service
@Transactional
public class AnualLeaveService {
		
	private AnualLeaveRepository repository;
	private StaffJpaRepository staffRepository;
	
	public AnualLeaveService(AnualLeaveRepository repository
							,StaffJpaRepository staffRepository) {
		this.repository = repository;
		this.staffRepository = staffRepository;
	}
	
	public AnualLeave getAnualLeave(String organizationCode, String staffNo, Integer yyyy) {
		Staff staff = findStaff(organizationCode, staffNo);
		return this.repository.findById(new AnualLeaveId(staff, yyyy)).orElse(null);		
	}
	
	public void saveAnualLeave(AnualLeaveDTO.SaveAnualLeave dto) {
		Staff staff = findStaff(dto.organizationCode(), dto.staffNo());
		AnualLeave entity = this.getAnualLeave(dto.organizationCode(), dto.staffNo(), dto.yyyy());
		
		if (entity == null) {
			entity = dto.newAnualLeave(staff);
		} else {
			dto.modifyEntity(entity);
		}
		
		this.repository.save(entity);
	}
	
	public void deleteAnualLeave(String organizationCode, String staffId, Integer yyyy) {
		AnualLeave entity = this.getAnualLeave(organizationCode, staffId, yyyy);
		
		this.repository.delete(entity);
	}
	
	
	private Staff findStaff(String organizationCode, String staffNo) {
		return staffRepository.findById(new StaffId(organizationCode, staffNo))
				 .orElseThrow(() -> new EntityNotFoundException(staffNo + " 직원번호가 존재하지 않습니다."));
	}
	
}
