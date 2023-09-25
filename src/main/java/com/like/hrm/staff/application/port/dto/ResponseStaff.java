package com.like.hrm.staff.application.port.dto;

import java.time.LocalDate;

import com.like.hrm.staff.domain.model.Staff;

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
			
	public static ResponseStaff toDTO(Staff entity) {
		
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