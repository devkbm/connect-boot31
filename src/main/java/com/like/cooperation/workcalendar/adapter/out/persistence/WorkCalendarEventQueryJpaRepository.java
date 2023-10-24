package com.like.cooperation.workcalendar.adapter.out.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.cooperation.workcalendar.domain.WorkCalendarEvent;
import com.like.cooperation.workcalendar.application.dto.WorkCalendarEventDTO;
import com.like.cooperation.workcalendar.application.port.in.WorkCalendarEventQueryUseCase;
import com.like.cooperation.workcalendar.domain.QWorkCalendarEvent;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class WorkCalendarEventQueryJpaRepository implements WorkCalendarEventQueryUseCase {
	private JPAQueryFactory queryFactory;
	private final QWorkCalendarEvent qWorkCalendarEvent = QWorkCalendarEvent.workCalendarEvent;
	
	public WorkCalendarEventQueryJpaRepository(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;		
	}
	
	@Override
	public List<WorkCalendarEvent> getScheduleList(WorkCalendarEventDTO.Search searchCondition) {
		return queryFactory
				.selectFrom(qWorkCalendarEvent)
				.where(searchCondition.getBooleanBuilder())
				.fetch();
	}
	
}
