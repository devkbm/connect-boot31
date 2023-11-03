package com.like.cooperation.workcalendar.adapter.out.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.cooperation.workcalendar.domain.WorkCalendarEvent;
import com.like.cooperation.workcalendar.application.port.dto.WorkCalendarEventQueryDTO;
import com.like.cooperation.workcalendar.application.port.out.WorkCalendarEventQueryDbPort;
import com.like.cooperation.workcalendar.domain.QWorkCalendarEvent;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class WorkCalendarEventQueryDbAdapter implements WorkCalendarEventQueryDbPort {
	private JPAQueryFactory queryFactory;
	private final QWorkCalendarEvent qWorkCalendarEvent = QWorkCalendarEvent.workCalendarEvent;
	
	public WorkCalendarEventQueryDbAdapter(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;		
	}
	
	@Override
	public List<WorkCalendarEvent> getScheduleList(WorkCalendarEventQueryDTO searchCondition) {
		return queryFactory
				.selectFrom(qWorkCalendarEvent)
				.where(searchCondition.getBooleanBuilder())
				.fetch();
	}
	
}
