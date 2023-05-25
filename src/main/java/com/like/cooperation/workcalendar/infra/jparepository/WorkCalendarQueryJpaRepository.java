package com.like.cooperation.workcalendar.infra.jparepository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.cooperation.workcalendar.boundary.WorkCalendarDTO;
import com.like.cooperation.workcalendar.domain.WorkCalendar;
import com.like.cooperation.workcalendar.domain.WorkCalendarQueryRepository;
import com.like.cooperation.workcalendar.domain.QWorkCalendar;
import com.like.cooperation.workcalendar.domain.QWorkCalendarMember;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class WorkCalendarQueryJpaRepository implements WorkCalendarQueryRepository {
	private JPAQueryFactory queryFactory;
	private final QWorkCalendar qWorkCalendar = QWorkCalendar.workCalendar;
	private final QWorkCalendarMember qWorkCalendarMember = QWorkCalendarMember.workCalendarMember;
	
	public WorkCalendarQueryJpaRepository(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;		
	}
	
	@Override
	public List<WorkCalendar> getWorkGroupList(WorkCalendarDTO.Search searchCondition) {
		return queryFactory
				.selectFrom(qWorkCalendar)
				.where(searchCondition.getBooleanBuilder())
				.fetch();
	}

	@Override
	public List<WorkCalendar> getWorkGroupList(String userId) {

		return queryFactory
				.selectFrom(qWorkCalendar)
				.join(qWorkCalendar.memberList, qWorkCalendarMember)
				.where(qWorkCalendarMember.user.id.eq(userId))
				.fetch();
	}
	
	
}
