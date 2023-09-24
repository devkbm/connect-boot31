package com.like.hrm.staff.application.service;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.staff.application.port.dto.AppointmentRecordDTO;
import com.like.hrm.staff.domain.model.Staff;
import com.like.hrm.staff.domain.model.StaffId;
import com.like.hrm.staff.domain.model.StaffRepository;
import com.like.hrm.staff.domain.model.appointment.AppointmentRecord;
import com.like.hrm.staff.domain.model.appointment.AppointmentRecordList;

@Transactional
@Service
public class StaffAppointmentService {

	private StaffRepository repository;
	
	public StaffAppointmentService(StaffRepository repository) {
		this.repository = repository;		
	}
	
	public AppointmentRecordList getAppointmentRecord(String organizationCode, String staffNo) {
		return findStaff(organizationCode, staffNo).getAppointmentRecordList();
	}
	
	public AppointmentRecord getAppointmentRecord(String organizationCode, String staffNo, Long seq) {
		Staff staff = findStaff(organizationCode, staffNo);
		
		return staff.getAppointmentRecordList().get(staff, seq);	
	}
	
	public void saveAppointmentRecord(AppointmentRecordDTO.FormStaffAppointmentRecord dto) {
		Staff staff = findStaff(dto.organizationCode(), dto.staffNo());		
		AppointmentRecord entity = staff.getAppointmentRecordList().get(staff, dto.seq());
		
		if (entity == null) {
			entity = dto.newEntity(staff);
		} else {
			dto.modifyEntity(entity);
		}
		
		staff.getAppointmentRecordList().add(entity);		
		staff.applyAppointmentRecord(entity);		 
		
		repository.save(staff);
	}	
	
	public void applyAppointmentRecord(String organizationCode, String staffId, Long seq) {
		Staff staff = findStaff(organizationCode, staffId);
		AppointmentRecord entity = staff.getAppointmentRecordList().get(staff, seq);
		
		staff.applyAppointmentRecord(entity);					
	}
	
	private Staff findStaff(String organizationCode, String staffNo) {
		return repository.findById(new StaffId(organizationCode, staffNo))
						 .orElseThrow(() -> new EntityNotFoundException(staffNo + " 직원번호가 존재하지 않습니다."));
	}
		
}
