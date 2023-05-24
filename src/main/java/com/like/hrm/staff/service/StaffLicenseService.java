package com.like.hrm.staff.service;

import java.util.List;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.staff.boundary.LicenseDTO;
import com.like.hrm.staff.domain.model.Staff;
import com.like.hrm.staff.domain.model.StaffRepository;
import com.like.hrm.staff.domain.model.license.StaffLicense;

@Transactional
@Service
public class StaffLicenseService {

	private StaffRepository repository;
	
	public StaffLicenseService(StaffRepository repository) {
		this.repository = repository;	
	}
	
	public List<StaffLicense> getLicenseList(String staffId) {
		Staff staff = findStaff(staffId);
		
		return staff.getLicenseList().getStream().toList();
	}
	
	
	public StaffLicense getLicense(String staffId, Long seq) {
		Staff staff = findStaff(staffId);
						
		return staff.getLicenseList().get(staff, seq);
	}
	
	public void saveLicense(LicenseDTO.Form dto) {
		Staff staff = findStaff(dto.staffId());		
		StaffLicense license = staff.getLicenseList().get(staff, dto.seq());
		
		if (license == null) {
			license = dto.newEntity(staff);
		} else {
			dto.modifyEntity(license);
		}
		
		staff.getLicenseList().add(license);
		
		repository.save(staff);
	}
	
	public void deleteLicense(String staffId, Long seq) {
		Staff staff = findStaff(staffId);
		
		staff.getLicenseList().remove(staff, seq);
	}
	
	private Staff findStaff(String staffId) {
		return repository.findById(staffId)
				 .orElseThrow(() -> new EntityNotFoundException(staffId + " 직원번호가 존재하지 않습니다."));
	}
}
