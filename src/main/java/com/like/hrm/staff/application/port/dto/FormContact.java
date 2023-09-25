package com.like.hrm.staff.application.port.dto;

import java.util.Optional;

import com.like.hrm.staff.domain.model.Staff;
import com.like.hrm.staff.domain.model.StaffContact;
import com.like.system.core.jpa.vo.Address;
import com.like.system.core.jpa.vo.PhoneNumber;

import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record FormContact(
		String clientAppUrl,
		String organizationCode,						
		@NotEmpty String staffNo,
		String staffName,
		String homeAddressType,
		String homePostNumber,
		String homeMainAddress,
		String homeSubAddress,
		String extensionNumber,
		String mobileNumber
		) {
	
	public StaffContact newEntity() {		
		return new StaffContact(new Address(homeAddressType, homePostNumber, homeMainAddress, extensionNumber), new PhoneNumber(extensionNumber), new PhoneNumber(mobileNumber));
	}
				
	public static FormContact convert(Staff entity) {			
		if (entity == null) return null;
		
		Optional<StaffContact> contact = Optional.ofNullable(entity.getContact());
							
		return FormContact.builder()
						  .organizationCode(entity.getId().getOrganizationCode())	
				 		  .staffNo(entity.getId().getStaffNo())
				 		  .homeAddressType(contact.map(StaffContact::getHome).map(Address::getAddress_type).orElse(null))
				 		  .homePostNumber(contact.map(StaffContact::getHome).map(Address::getPost_number).orElse(null))
				 		  .homeMainAddress(contact.map(StaffContact::getHome).map(Address::getMain_address).orElse(null))
				 		  .homeSubAddress(contact.map(StaffContact::getHome).map(Address::getSub_address).orElse(null))					 		  
				 		  .extensionNumber(contact.map(StaffContact::getExtensionNumber).map(PhoneNumber::getNumber).orElse(null))
				 		  .mobileNumber(contact.map(StaffContact::getMobileNumber).map(PhoneNumber::getNumber).orElse(null))					 		  					 		  					 		  					 		
						  .build();
						  
	}
}