package com.like.hrm.staff.adapter.out.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.hrm.staff.adapter.out.persistence.jpa.repository.StaffJpaRepository;
import com.like.hrm.staff.application.port.dto.StaffAppointmentRecordDTO;
import com.like.hrm.staff.application.port.out.StaffAppointmentSelectDbPort;
import com.like.hrm.staff.domain.model.Staff;
import com.like.hrm.staff.domain.model.StaffId;

import jakarta.persistence.EntityNotFoundException;

@Repository
public class StaffAppointmentSelectDbAdapter implements StaffAppointmentSelectDbPort {
	
	StaffJpaRepository repository;
	
	StaffAppointmentSelectDbAdapter(StaffJpaRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public StaffAppointmentRecordDTO select(String organizationCode, String staffNo, Long seq) {
		Staff staff = findStaff(organizationCode, staffNo);
				
		return StaffAppointmentRecordDTO.convert(staff.getAppointmentRecordList().get(staff, seq));			
	}

	@Override
	public List<StaffAppointmentRecordDTO> select(String organizationCode, String staffNo) {		
		return findStaff(organizationCode, staffNo).getAppointmentRecordList()
												   .getStream()
												   .map(e -> StaffAppointmentRecordDTO.convert(e))
												   .toList();						
	}
	
	private Staff findStaff(String organizationCode, String staffNo) {
		return repository.findById(new StaffId(organizationCode, staffNo))
						 .orElseThrow(() -> new EntityNotFoundException(staffNo + " 직원번호가 존재하지 않습니다."));
	}

}
