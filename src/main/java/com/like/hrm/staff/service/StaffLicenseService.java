package com.like.hrm.staff.service;

import java.util.List;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.staff.boundary.LicenseDTO;
import com.like.hrm.staff.domain.model.Staff;
import com.like.hrm.staff.domain.model.StaffId;
import com.like.hrm.staff.domain.model.StaffRepository;
import com.like.hrm.staff.domain.model.license.StaffLicense;

@Transactional
@Service
public class StaffLicenseService {

	private StaffRepository repository;
	
	public StaffLicenseService(StaffRepository repository) {
		this.repository = repository;	
	}
	
	public List<StaffLicense> getLicenseList(String organizationCode, String staffNo) {
		Staff staff = findStaff(organizationCode, staffNo);
		
		return staff.getLicenseList().getStream().toList();
	}
	
	
	public StaffLicense getLicense(String organizationCode, String staffNo, Long seq) {
		Staff staff = findStaff(organizationCode, staffNo);
						
		return staff.getLicenseList().get(staff, seq);
	}
	
	public void saveLicense(LicenseDTO.Form dto) {
		Staff staff = findStaff(dto.organizationCode(), dto.staffNo());		
		StaffLicense license = staff.getLicenseList().get(staff, dto.seq());
		
		if (license == null) {
			license = dto.newEntity(staff);
		} else {
			dto.modifyEntity(license);
		}
		
		staff.getLicenseList().add(license);
		
		repository.save(staff);
	}
	
	public void deleteLicense(String organizationCode, String staffNo, Long seq) {
		Staff staff = findStaff(organizationCode, staffNo);
		
		staff.getLicenseList().remove(staff, seq);
	}
	
	private Staff findStaff(String organizationCode, String staffNo) {
		return repository.findById(new StaffId(organizationCode, staffNo))
				 .orElseThrow(() -> new EntityNotFoundException(staffNo + " 직원번호가 존재하지 않습니다."));
	}
}
