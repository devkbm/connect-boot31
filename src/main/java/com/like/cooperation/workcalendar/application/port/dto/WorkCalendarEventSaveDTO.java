package com.like.cooperation.workcalendar.application.port.dto;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.like.cooperation.workcalendar.domain.WorkCalendar;
import com.like.cooperation.workcalendar.domain.WorkCalendarEvent;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record WorkCalendarEventSaveDTO(
		LocalDateTime createdDt,
		String createdBy,
		LocalDateTime modifiedDt,
		String modifiedBy,
		String clientAppUrl,		
		String organizationCode,			
		Long id,					
		@NotEmpty
		String text,						
		@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")			
		LocalDateTime start,			
		@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
		LocalDateTime end,			
		Boolean allDay,			
		@NotNull
		Long workCalendarId
		) {
	
	public WorkCalendarEvent newSchedule(WorkCalendar workCalendar) {
		
		WorkCalendarEvent entity = WorkCalendarEvent.builder()
								  .title(this.text)
								  .start(this.start)
								  .end(this.end)
								  .allDay(this.allDay)
								  .workCalendar(workCalendar)
								  .build();
		entity.setAppUrl(clientAppUrl);
		return entity;
	}
	
	public void modifySchedule(WorkCalendarEvent entity) {
		entity.modifyEntity(text, start, end, allDay);
		entity.setAppUrl(clientAppUrl);
	}
	
	public static WorkCalendarEventSaveDTO convertDTO(WorkCalendarEvent entity) {
		WorkCalendarEventSaveDTO dto = WorkCalendarEventSaveDTO.builder()
					   .id(entity.getId())
					   .text(entity.getTitle())
					   .start(entity.getStart())
					   .end(entity.getEnd())
					   .allDay(entity.getAllDay())
					   .workCalendarId(entity.getWorkCalendar().getId())
					   .build();
														
		return dto;
	}
}