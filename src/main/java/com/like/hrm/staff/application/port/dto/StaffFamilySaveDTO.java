package com.like.hrm.staff.application.port.dto;

import com.like.hrm.staff.domain.model.Staff;
import com.like.hrm.staff.domain.model.family.StaffFamily;

import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record StaffFamilySaveDTO(
		String clientAppUrl,
		String organizationCode,			
		@NotEmpty String staffNo,
		String staffName,
		Long seq,
		String familyName,
		String familyRRN,
		String familyRelation,
		String occupation,
		String schoolCareerType,
		String comment
		) {
	
	public StaffFamily newEntity(Staff staff) {
		StaffFamily entity = StaffFamily.builder()
										.staff(staff)
										.name(familyName)
										.residentRegistrationNumber(familyRRN)
										.relation(familyRelation)
										.occupation(occupation)
										.schoolCareerType(schoolCareerType)
										.comment(comment)
										.build();
		entity.setAppUrl(clientAppUrl);
		return entity; 							
	}
	
	public void modifyEntity(StaffFamily entity) {
		entity.modifyBuilder()
		      .name(familyName)
		      .residentRegistrationNumber(familyRRN)
		      .relation(familyRelation)
		      .occupation(occupation)
		      .schoolCareerType(schoolCareerType)
		      .comment(comment)
		      .modify();		
		
		entity.setAppUrl(clientAppUrl);
	}
	
	public static StaffFamilySaveDTO toDTO(StaffFamily entity) {
		if (entity == null) return null;
		
		return StaffFamilySaveDTO.builder()		
				   .organizationCode(entity.getStaff().getId().getOrganizationCode())
				   .staffNo(entity.getStaff().getId().getStaffNo())
				   .staffName(entity.getStaff().getName().getName())
				   .seq(entity.getId().getSeq())
				   .familyName(entity.getName())
				   .familyRRN(entity.getResidentRegistrationNumber())
				   .familyRelation(entity.getRelation())
				   .occupation(entity.getOccupation())
				   .schoolCareerType(entity.getSchoolCareerType())
				   .comment(entity.getComment())
				   .build();									 							 							 							 					
	}
}