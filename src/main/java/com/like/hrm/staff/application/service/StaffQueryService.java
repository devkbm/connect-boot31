package com.like.hrm.staff.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.staff.application.port.dto.ResponseStaffAppointmentRecord;
import com.like.hrm.staff.application.port.dto.ResponseStaffCurrentAppointment;
import com.like.hrm.staff.application.port.dto.ResponseStaffDutyResponsibility;
import com.like.hrm.staff.application.port.dto.StaffQueryConditionDTO;
import com.like.hrm.staff.domain.model.Staff;
import com.like.hrm.staff.domain.model.StaffQueryRepository;

@Service
@Transactional(readOnly = true)
public class StaffQueryService {

	private StaffQueryRepository repository;
	
	public StaffQueryService(StaffQueryRepository repository) {
		this.repository = repository;		
	}
	
	public List<Staff> getStaff(StaffQueryConditionDTO dto) {
		return repository.getStaffList(dto);
	}
	
	public List<ResponseStaffAppointmentRecord> getStaffAppointmentRecordList(String organizationCode, String staffNo) {
		return repository.getStaffAppointmentRecordList(organizationCode, staffNo);
	}
	
	public ResponseStaffCurrentAppointment getStaffCurrentAppointment(String organizationCode, String staffNo) {
		return repository.getStaffCurrentAppointment(organizationCode, staffNo);
	}
	
	public List<ResponseStaffDutyResponsibility> getStaffDutyResponsibility(String organizationCode, String staffNo) {
		return repository.getStaffDutyResponsibility(organizationCode, staffNo);
	}
}
