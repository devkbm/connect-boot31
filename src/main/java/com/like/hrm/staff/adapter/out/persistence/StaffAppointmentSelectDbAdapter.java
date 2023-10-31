package com.like.hrm.staff.adapter.out.persistence;

import org.springframework.stereotype.Repository;

import com.like.hrm.staff.adapter.out.persistence.jpa.repository.StaffJpaRepository;
import com.like.hrm.staff.application.port.out.StaffAppointmentSelectDbPort;
import com.like.hrm.staff.domain.model.Staff;
import com.like.hrm.staff.domain.model.StaffId;
import com.like.hrm.staff.domain.model.appointment.AppointmentRecord;

import jakarta.persistence.EntityNotFoundException;

@Repository
public class StaffAppointmentSelectDbAdapter implements StaffAppointmentSelectDbPort {
	
	StaffJpaRepository repository;
	
	StaffAppointmentSelectDbAdapter(StaffJpaRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public AppointmentRecord select(String organizationCode, String staffNo, Long seq) {
		Staff staff = repository.findById(new StaffId(organizationCode, staffNo))
				 				.orElseThrow(() -> new EntityNotFoundException(staffNo + " 직원번호가 존재하지 않습니다."));
				
		return staff.getAppointmentRecordList().get(staff, seq);			
	}	

}
