package com.like.hrm.staff.adapter.out.persistence;

import org.springframework.stereotype.Repository;

import com.like.hrm.staff.adapter.out.persistence.jpa.repository.StaffJpaRepository;
import com.like.hrm.staff.application.port.dto.ResponseStaff;
import com.like.hrm.staff.application.port.out.StaffSelectDbPort;
import com.like.hrm.staff.domain.model.Staff;
import com.like.hrm.staff.domain.model.StaffId;

@Repository
public class StaffSelectDbAdapter implements StaffSelectDbPort {

	StaffJpaRepository repository;
	
	StaffSelectDbAdapter(StaffJpaRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public ResponseStaff select(String organizationCode, String id) {
		Staff entity = this.repository.findById(new StaffId(organizationCode, id)).orElse(null);
		return ResponseStaff.toDTO(entity);
	}

}
