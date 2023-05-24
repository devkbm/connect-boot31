package com.like.hrm.staff.boundary;

import jakarta.validation.constraints.NotEmpty;

import com.like.hrm.staff.domain.model.Staff;
import com.like.hrm.staff.domain.model.family.StaffFamily;

import lombok.AccessLevel;
import lombok.Builder;

public class FamilyDTO {

	@Builder(access = AccessLevel.PRIVATE)
	public static record Form(
			String clientAppUrl,
			String organizationCode,			
			@NotEmpty String staffId,
			String staffNo,
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
		
		public static Form convert(StaffFamily entity) {
			if (entity == null) return null;
			
			return Form.builder()
					   .staffId(entity.getStaff().getId())
					   .staffNo(entity.getStaff().getStaffNo())
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
}
