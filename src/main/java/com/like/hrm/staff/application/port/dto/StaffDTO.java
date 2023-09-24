package com.like.hrm.staff.application.port.dto;

import java.time.LocalDate;
import java.util.Optional;

import jakarta.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.like.hrm.staff.domain.model.Staff;
import com.like.hrm.staff.domain.model.StaffContact;
import com.like.hrm.staff.domain.model.StaffName;
import com.like.system.core.jpa.vo.Address;
import com.like.system.core.jpa.vo.PhoneNumber;

import lombok.AccessLevel;
import lombok.Builder;

public class StaffDTO {
		
	public record ResponseStaff(			
			String organizationCode,
			String staffNo,
			String name,
			String nameEng,
			String nameChi,
			String residentRegistrationNumber,
			String gender,
			LocalDate birthday,
			String imagePath
			) {
				
		public static ResponseStaff convert(Staff entity) {
			
			if (entity == null) return null;			
			
			var name = entity.getName();
			
			return new ResponseStaff(entity.getId().getOrganizationCode()
									,entity.getId().getStaffNo()
								   	,name.getName()
								   	,name.getNameEng()
								   	,name.getNameChi()
								   	,entity.getResidentRegistrationNumber().getNumber()
								   	,entity.getGender()
								   	,entity.getBirthday()
								   	,entity.getImagePath());								   
								   
		}
	}
			
	@JsonIgnoreProperties(ignoreUnknown = true)
	public record FormStaff(
			String organizationCode,
			@NotEmpty(message = "직원번호는 필수 입력 값입니다.")
			String staffNo,
			String name,
			String nameEng,
			String nameChi,
			String gender,
			LocalDate birthday
			) {
		
		public void modifyEntity(Staff entity) {
			entity.modifyEntity(new StaffName(name, nameEng, nameChi)
					 		   ,this.birthday);
		}
	}
	
	@Builder(access = AccessLevel.PRIVATE)
	public static record FormContact(
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

}
