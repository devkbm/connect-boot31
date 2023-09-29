package com.like.hrm.staff.application.port.dto;

import java.time.LocalDate;

import com.like.hrm.staff.domain.model.Staff;
import com.like.hrm.staff.domain.model.schoolcareer.StaffSchoolCareer;

import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record StaffSchoolCareerSaveDTO(
		String clientAppUrl,
		String organizationCode,			
		@NotEmpty String staffNo,
		String staffName,
		Long seq,
		@NotEmpty String schoolCareerType,
		@NotEmpty String schoolCode,
		LocalDate fromDate,
		LocalDate toDate,
		String majorName,
		String pluralMajorName,
		String location,
		Integer lessonYear,
		String comment) {
	
	public StaffSchoolCareer newEntity(Staff staff) {
		StaffSchoolCareer entity = StaffSchoolCareer.builder()
													.staff(staff)
													.schoolCareerType(schoolCareerType)
													.schoolCode(schoolCode)
													.fromDate(fromDate)
													.toDate(toDate)
													.majorName(majorName)
													.pluralMajorName(pluralMajorName)
													.location(location)
													.lessonYear(lessonYear)
													.build(); 
		entity.setAppUrl(clientAppUrl);
		
		return entity; 					
	}
	
	public void modifyEnity(StaffSchoolCareer entity) {
		entity.modifyBuilder()
		      .schoolCareerType(schoolCareerType)
		      .schoolCode(schoolCode)
		      .fromDate(fromDate)
		      .toDate(toDate)
		      .majorName(majorName)
		      .pluralMajorName(pluralMajorName)
		      .location(location)
		      .lessonYear(lessonYear)
		      .comment(comment)
			  .modify();			
		
		entity.setAppUrl(clientAppUrl);
	}	
	
	public static StaffSchoolCareerSaveDTO toDTO(StaffSchoolCareer entity) {
		if (entity == null) return null;			
								
		return StaffSchoolCareerSaveDTO.builder()
				   .organizationCode(entity.getStaff().getId().getOrganizationCode())
				   .staffNo(entity.getStaff().getId().getStaffNo())
				   .staffName(entity.getStaff().getName().getName())
				   .seq(entity.getId().getSeq())
				   .schoolCareerType(entity.getSchoolCareerType())
				   .schoolCode(entity.getSchoolCode())
				   .fromDate(entity.getPeriod().getFrom())
				   .toDate(entity.getPeriod().getTo())
				   .majorName(entity.getMajorName())
				   .pluralMajorName(entity.getPluralMajorName())
				   .location(entity.getLocation())
				   .lessonYear(entity.getLessonYear())
				   .comment(entity.getComment())
				   .build();												 						
	}
}