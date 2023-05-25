package com.like.cooperation.workcalendar.boundary;

import static org.springframework.util.StringUtils.hasText;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.like.cooperation.workcalendar.domain.QWorkCalendarEvent;
import com.like.cooperation.workcalendar.domain.WorkCalendarEvent;
import com.like.cooperation.workcalendar.domain.WorkCalendar;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.Expressions;

import lombok.Builder;

public class WorkCalendarEventDTO {		
	
	public record Search(
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
	
	@Builder
	public static record Form(
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
		
		public static Form convertDTO(WorkCalendarEvent entity) {
			Form dto = Form.builder()
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
		
	
	@Builder
	public static record Response(
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
		
		public static Response convert(WorkCalendarEvent entity) {
			
			WorkCalendar workCalendar = entity.getWorkCalendar();
			
			Response dto = Response.builder()
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
		
}
