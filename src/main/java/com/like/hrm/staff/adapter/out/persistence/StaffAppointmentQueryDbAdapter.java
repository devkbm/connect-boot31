package com.like.hrm.staff.adapter.out.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.hrm.staff.adapter.out.persistence.jpa.repository.StaffJpaRepository;
import com.like.hrm.staff.application.port.out.StaffAppointmentQueryDbPort;
import com.like.hrm.staff.domain.model.Staff;
import com.like.hrm.staff.domain.model.StaffId;
import com.like.hrm.staff.domain.model.appointment.AppointmentRecord;

import jakarta.persistence.EntityNotFoundException;

@Repository
public class StaffAppointmentQueryDbAdapter implements StaffAppointmentQueryDbPort {

	StaffJpaRepository repository;
	
	StaffAppointmentQueryDbAdapter(StaffJpaRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public List<AppointmentRecord> select(String organizationCode, String staffNo) {
		return findStaff(organizationCode, staffNo).getAppointmentRecordList().getStream().toList();	
	}
	
	private Staff findStaff(String organizationCode, String staffNo) {
		return repository.findById(new StaffId(organizationCode, staffNo))
						 .orElseThrow(() -> new EntityNotFoundException(staffNo + " 직원번호가 존재하지 않습니다."));
	}

}
