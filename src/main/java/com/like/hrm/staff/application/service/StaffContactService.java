package com.like.hrm.staff.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.staff.application.port.dto.FormContact;
import com.like.hrm.staff.domain.model.Staff;
import com.like.hrm.staff.domain.model.StaffContact;
import com.like.hrm.staff.domain.model.StaffId;
import com.like.hrm.staff.domain.model.StaffRepository;
import com.like.system.core.jpa.vo.Address;
import com.like.system.core.jpa.vo.PhoneNumber;

@Service
@Transactional
public class StaffContactService {

	private StaffRepository repository;	
	
	public StaffContactService(StaffRepository repository) {
		this.repository = repository;
	}
	
	public FormContact get(String organizationCode, String staffNo) {
		Staff staff = repository.findById(new StaffId(organizationCode,staffNo)).orElse(null);
		
		return FormContact.convert(staff);
	}
	
	public void save(FormContact dto) {
		Staff staff = repository.findById(new StaffId(dto.organizationCode(), dto.staffNo())).orElseThrow(() -> new IllegalArgumentException("직원정보가 존재하지 않습니다."));
						
		staff.changeContact(new StaffContact(new Address(dto.homeAddressType(), dto.homePostNumber(), dto.homeMainAddress(), dto.homeSubAddress())
						   ,new PhoneNumber(dto.extensionNumber())
						   ,new PhoneNumber(dto.mobileNumber())));
		
		this.repository.save(staff);
	}
	
	
}
