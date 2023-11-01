package com.like.cooperation.workcalendar.application.port.dto;

import static org.springframework.util.StringUtils.hasText;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.like.cooperation.workcalendar.domain.QWorkCalendarEvent;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.Expressions;

import jakarta.validation.constraints.NotEmpty;

public record WorkCalendarEventQueryDTO(
		@NotEmpty
		String fkWorkCalendar,
		@NotEmpty
		String fromDate,
		@NotEmpty
		String toDate,
		String title
		) {
	
	private static final QWorkCalendarEvent qWorkCalendarEvent = QWorkCalendarEvent.workCalendarEvent;
	
	public BooleanBuilder getBooleanBuilder() {
		BooleanBuilder builder = new BooleanBuilder();
																								
		LocalDateTime fromDateTime = getFromDate(this.fromDate);			
		LocalDateTime toDateTime = getToDate(this.toDate);			
		
		DateTimeExpression<LocalDateTime> fromExpression = Expressions.asDateTime(fromDateTime);
		DateTimeExpression<LocalDateTime> toExpression = Expressions.asDateTime(toDateTime);
		
		//DateTimeExpression<LocalDateTime> monthEndDay = Expressions.asDateTime(param.with(TemporalAdjusters.lastDayOfMonth()));					
		// LocalDateTime firstDay = param.with(TemporalAdjusters.firstDayOfMonth());																					
		
		builder.and(fromExpression.between(qWorkCalendarEvent.start, qWorkCalendarEvent.end)
					.or(toExpression.between(qWorkCalendarEvent.start, qWorkCalendarEvent.end))
					.or(qWorkCalendarEvent.start.between(fromExpression, toExpression))
					.or(qWorkCalendarEvent.end.between(fromExpression, toExpression)));
			
		builder.and(inWorkgroupIds(this.changeIdType(this.fkWorkCalendar)))
		       .and(likeTitle(this.title));
										
		return builder;
	}					
	
	private BooleanExpression inWorkgroupIds(List<Long> ids) {
		if ( CollectionUtils.isEmpty(ids) ) {
			return null;
		}
		
		return qWorkCalendarEvent.workCalendar.id.in(ids);
	}
	
	private BooleanExpression likeTitle(String title) {
		return hasText(title) ? qWorkCalendarEvent.title.like("%"+title+"%") : null;					
	}
	
	/**
	 * 콤마로 구분된 id 매개변수를 List<Long>타입으로 변환한다. 
	 * @param params			ex) 1,2,3
	 * @return List<Long>
	 */
	private List<Long> changeIdType(String params) {
		
		String idArray[] = params.split(","); 			
	
		List<Long> ids = new ArrayList<Long>(idArray.length);
		
		for (int i=0; i<idArray.length; i++) {
			ids.add(Long.parseLong(idArray[i]));
		}	
		
		return ids;
	}
	
	private LocalDateTime getFromDate(String fromDate) {
		return LocalDateTime.of(
				Integer.parseInt(fromDate.substring(0, 4)), 
				Integer.parseInt(fromDate.substring(4, 6)), 
				Integer.parseInt(fromDate.substring(6, 8)), 
				0, 
				0, 
				0,
				0);
	}
	
	private LocalDateTime getToDate(String toDate) {
		return LocalDateTime.of(
				Integer.parseInt(toDate.substring(0, 4)), 
				Integer.parseInt(toDate.substring(4, 6)), 
				Integer.parseInt(toDate.substring(6, 8)), 
				23, 
				59, 
				59,
				59);		
	}
}