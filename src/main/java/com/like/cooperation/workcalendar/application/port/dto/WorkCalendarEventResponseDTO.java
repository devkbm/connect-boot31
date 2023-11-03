package com.like.cooperation.workcalendar.application.port.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.like.cooperation.workcalendar.domain.WorkCalendar;
import com.like.cooperation.workcalendar.domain.WorkCalendarEvent;

import lombok.Builder;

@Builder
public record WorkCalendarEventResponseDTO(
		LocalDateTime createdDt,
		String createdBy,
		LocalDateTime modifiedDt,
		String modifiedBy,
		Long workCalendarId,
		Long id,
		String text,
		String color,
		//@DateTimeFormat(pattern="E MMM dd yyyy HH:mm:ss 'GMT'Z")
		//@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		LocalDateTime start,
		//@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		LocalDateTime end,
		Boolean allDay
		) {
	
	public static WorkCalendarEventResponseDTO toDTO
	(WorkCalendarEvent entity) {
		
		WorkCalendar workCalendar = entity.getWorkCalendar();
		
		WorkCalendarEventResponseDTO dto = WorkCalendarEventResponseDTO.builder()
							   .workCalendarId(workCalendar.getId())
							   .id(entity.getId())
							   .text(entity.getTitle())
							   .color(workCalendar.getColor())
							   .start(entity.getStart())
							   .end(entity.getEnd())
							   .allDay(entity.getAllDay())																							
							   .build();
																
		return dto;
	}
}