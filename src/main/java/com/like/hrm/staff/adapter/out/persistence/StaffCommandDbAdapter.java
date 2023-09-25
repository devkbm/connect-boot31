package com.like.hrm.staff.adapter.out.persistence;

import org.springframework.stereotype.Repository;

import com.like.hrm.staff.adapter.out.persistence.jpa.repository.StaffJpaRepository;
import com.like.hrm.staff.application.port.out.StaffCommandDbPort;
import com.like.hrm.staff.domain.model.Staff;
import com.like.hrm.staff.domain.model.StaffId;

@Repository
public class StaffCommandDbAdapter implements StaffCommandDbPort {

	StaffJpaRepository repository;
	
	StaffCommandDbAdapter(StaffJpaRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public Staff select(String organizationCode, String staffNo) {
		return this.repository.findById(new StaffId(organizationCode, staffNo)).orElse(null);
	}

	@Override
	public void save(Staff entity) {
		this.repository.save(entity);		
	}

	@Override
	public void delete(String organizationCode, String staffNo) {
		this.repository.deleteById(new StaffId(organizationCode, staffNo));	
	}

}
