package com.like.cooperation.workcalendar.application.dto;

import static org.springframework.util.StringUtils.hasText;

import java.time.LocalDateTime;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;

import com.like.cooperation.workcalendar.domain.QWorkCalendar;
import com.like.cooperation.workcalendar.domain.WorkCalendar;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.Builder;


public class WorkCalendarDTO {	
	
	public record Search(
			String name
			) {
		
		private static final QWorkCalendar qWorkCalendar = QWorkCalendar.workCalendar;
		
		public BooleanBuilder getBooleanBuilder() {
			BooleanBuilder builder = new BooleanBuilder();
			
			builder.and(likeName(this.name));			
			
			return builder;
		}
		
		private BooleanExpression likeName(String name) {
			return hasText(name) ? qWorkCalendar.name.like("%"+this.name+"%") : null;			
		}
	}
	
	@Builder
	public static record Form(
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
		
		public static WorkCalendarDTO.Form convertDTO(WorkCalendar entity) {
			WorkCalendarDTO.Form dto = Form.builder()
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
	
	
	
	
}
