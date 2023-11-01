package com.like.cooperation.workcalendar.application.port.dto;

import static org.springframework.util.StringUtils.hasText;

import com.like.cooperation.workcalendar.domain.QWorkCalendar;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

public record WorkCalendarQueryDTO(
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