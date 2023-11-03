package com.like.cooperation.workcalendar.application.port.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.like.cooperation.workcalendar.domain.WorkCalendar;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record WorkCalendarSaveDTO(
		LocalDateTime createdDt,
		String createdBy,
		LocalDateTime modifiedDt,
		String modifiedBy,
		String clientAppUrl,
		String organizationCode,
		Long workCalendarId,
		@NotEmpty
		String workCalendarName,
		String color,
		List<String> memberList
		) {
	
	public WorkCalendar newWorkGroup() {
		WorkCalendar entity = new WorkCalendar(this.workCalendarName, this.color);
		entity.setAppUrl(clientAppUrl);
		return entity;
	}
	
	public void modifyWorkGroup(WorkCalendar workGroup) {
		workGroup.modifyEntity(this.workCalendarName, color);
		
		workGroup.setAppUrl(clientAppUrl);
	}
	
	public static WorkCalendarSaveDTO toDTO(WorkCalendar entity) {
		WorkCalendarSaveDTO dto = WorkCalendarSaveDTO.builder()
									   .workCalendarId(entity.getId())
									   .workCalendarName(entity.getName())
									   .color(entity.getColor())
									   .memberList(entity.getMemberList().stream()
										  	 						     .map(r -> r.getId().getUserId())
	 										 						     .toList())
									   .build();

		return dto;
	}
}