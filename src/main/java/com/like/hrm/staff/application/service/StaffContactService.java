package com.like.hrm.staff.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.staff.adapter.out.persistence.jpa.repository.StaffJpaRepository;
import com.like.hrm.staff.application.port.dto.StaffContactSaveDTO;
import com.like.hrm.staff.domain.model.Staff;
import com.like.hrm.staff.domain.model.StaffContact;
import com.like.hrm.staff.domain.model.StaffId;
import com.like.system.core.jpa.vo.Address;
import com.like.system.core.jpa.vo.PhoneNumber;

@Service
@Transactional
public class StaffContactService {

	private StaffJpaRepository repository;	
	
	public StaffContactService(StaffJpaRepository repository) {
		this.repository = repository;
	}
	
	public StaffContactSaveDTO get(String organizationCode, String staffNo) {
		Staff staff = repository.findById(new StaffId(organizationCode,staffNo)).orElse(null);
		
		return StaffContactSaveDTO.toDTO(staff);
	}
	
	public void save(StaffContactSaveDTO dto) {
		Staff staff = repository.findById(new StaffId(dto.organizationCode(), dto.staffNo())).orElseThrow(() -> new IllegalArgumentException("직원정보가 존재하지 않습니다."));
						
		staff.changeContact(new StaffContact(new Address(dto.homeAddressType(), dto.homePostNumber(), dto.homeMainAddress(), dto.homeSubAddress())
						   ,new PhoneNumber(dto.extensionNumber())
						   ,new PhoneNumber(dto.mobileNumber())));
		
		this.repository.save(staff);
	}
	
	
}
